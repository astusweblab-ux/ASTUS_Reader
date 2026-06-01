package com.astus.reader.feature_library

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.astus.reader.domain.model.Book
import com.astus.reader.feature_import.ImportFormats

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    viewModel: LibraryViewModel,
    onOpenBook: (String) -> Unit,
    onOpenSettings: () -> Unit,
    onOpenManual: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val importLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenMultipleDocuments()
    ) { uris ->
        viewModel.onIntent(LibraryIntent.ImportBooks(uris))
    }

    LaunchedEffect(state.error) {
        val error = state.error
        if (error != null) {
            snackbarHostState.showSnackbar(error)
            viewModel.onIntent(LibraryIntent.ClearError)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ASTUS Reader") },
                actions = {
                    IconButton(onClick = { importLauncher.launch(ImportFormats.MimeTypes) }) {
                        Icon(Icons.Default.FileUpload, contentDescription = "Import books")
                    }
                    IconButton(onClick = onOpenManual) {
                        Icon(Icons.AutoMirrored.Filled.HelpOutline, contentDescription = "Manual")
                    }
                    IconButton(onClick = onOpenSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                OutlinedTextField(
                    value = state.query,
                    onValueChange = { viewModel.onIntent(LibraryIntent.SearchChanged(it)) },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    singleLine = true,
                    label = { Text("Поиск") }
                )
            }

            item {
                ContinueReadingCard(
                    book = state.continueBook,
                    isImporting = state.isImporting,
                    onOpenBook = onOpenBook,
                    onImport = { importLauncher.launch(ImportFormats.MimeTypes) }
                )
            }

            item {
                Text(
                    text = "Библиотека",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }

            items(state.filteredBooks, key = { it.id }) { book ->
                BookRow(book = book, onClick = { onOpenBook(book.id) })
            }

            item {
                DeveloperCredit(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(top = 24.dp, bottom = 12.dp),
                    onOpenSite = {
                        context.startActivity(
                            Intent(Intent.ACTION_VIEW, "https://astuslab.com.ua/".toUri())
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun DeveloperCredit(
    modifier: Modifier = Modifier,
    onOpenSite: () -> Unit
) {
    val linkColor = MaterialTheme.colorScheme.primary
    Text(
        text = buildAnnotatedString {
            append("Разработчик ")
            withStyle(SpanStyle(color = linkColor, fontWeight = FontWeight.SemiBold)) {
                append("ASTUS LAB WEB STUDIO")
            }
        },
        modifier = modifier.clickable(onClick = onOpenSite),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.68f)
    )
}

@Composable
private fun ContinueReadingCard(
    book: Book?,
    isImporting: Boolean,
    onOpenBook: (String) -> Unit,
    onImport: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BookCover(book = book, modifier = Modifier.size(width = 72.dp, height = 104.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text("Продолжить чтение", style = MaterialTheme.typography.labelLarge)
                Spacer(Modifier.height(6.dp))
                Text(
                    text = book?.title ?: "Импортируйте первую книгу",
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = book?.author ?: "TXT, FB2, EPUB, PDF, DOCX, HTML, MD и RTF",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            if (isImporting) {
                CircularProgressIndicator(modifier = Modifier.size(32.dp))
            } else {
                Button(onClick = { book?.let { onOpenBook(it.id) } ?: onImport() }) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null)
                    Text(if (book == null) "Импорт" else "Открыть")
                }
            }
        }
    }
}

@Composable
private fun BookRow(book: Book, onClick: () -> Unit) {
    ListItem(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick),
        leadingContent = { BookCover(book = book, modifier = Modifier.size(width = 48.dp, height = 68.dp)) },
        headlineContent = {
            Text(book.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
        },
        supportingContent = {
            Text("${book.author} • ${book.format}", maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
    )
}

@Composable
private fun BookCover(book: Book?, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .aspectRatio(0.68f)
            .clip(RoundedCornerShape(6.dp))
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.18f)),
        contentAlignment = Alignment.Center
    ) {
        if (book?.coverUri != null) {
            AsyncImage(model = book.coverUri, contentDescription = book.title, modifier = Modifier.fillMaxSize())
        } else {
            Icon(
                imageVector = Icons.Default.Book,
                contentDescription = null,
                tint = Color(0xFF4F5D2F),
                modifier = Modifier.size(28.dp)
            )
        }
    }
}
