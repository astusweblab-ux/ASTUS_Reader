package com.astus.reader.feature_library

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astus.reader.core.datastore.SettingsDataStore
import com.astus.reader.domain.model.Book
import com.astus.reader.domain.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LibraryState(
    val books: List<Book> = emptyList(),
    val query: String = "",
    val lastBookId: String? = null,
    val isImporting: Boolean = false,
    val error: String? = null
) {
    val filteredBooks: List<Book>
        get() = if (query.isBlank()) {
            books
        } else {
            books.filter {
                it.title.contains(query, ignoreCase = true) ||
                    it.author.contains(query, ignoreCase = true)
            }
        }

    val continueBook: Book?
        get() = books.firstOrNull { it.id == lastBookId } ?: books.firstOrNull()
}

sealed interface LibraryIntent {
    data class SearchChanged(val query: String) : LibraryIntent
    data class ImportBooks(val uris: List<Uri>) : LibraryIntent
    data object ClearError : LibraryIntent
}

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val repository: BookRepository,
    settingsDataStore: SettingsDataStore
) : ViewModel() {
    private val localState = MutableStateFlow(LibraryState())
    private val coverRefreshAttempts = mutableSetOf<String>()

    val state: StateFlow<LibraryState> = combine(
        localState,
        repository.observeBooks(),
        settingsDataStore.settings
    ) { local, books, settings ->
        refreshMissingCoversOnce(books)
        local.copy(books = books, lastBookId = settings.lastBookId)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), LibraryState())

    fun onIntent(intent: LibraryIntent) {
        when (intent) {
            is LibraryIntent.SearchChanged -> localState.update { it.copy(query = intent.query) }
            is LibraryIntent.ImportBooks -> importBooks(intent.uris)
            LibraryIntent.ClearError -> localState.update { it.copy(error = null) }
        }
    }

    private fun importBooks(uris: List<Uri>) {
        if (uris.isEmpty()) return
        viewModelScope.launch {
            localState.update { it.copy(isImporting = true, error = null) }
            runCatching { repository.importBooks(uris) }
                .onFailure { error -> localState.update { it.copy(error = error.message ?: "Import failed") } }
            localState.update { it.copy(isImporting = false) }
        }
    }

    private fun refreshMissingCoversOnce(books: List<Book>) {
        val candidates = books.filter { it.coverUri == null && coverRefreshAttempts.add(it.id) }
        if (candidates.isEmpty()) return
        viewModelScope.launch {
            repository.refreshMissingCovers(candidates)
        }
    }
}
