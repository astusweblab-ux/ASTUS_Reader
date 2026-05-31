package com.astus.reader.domain.usecase

import com.astus.reader.domain.repository.BookRepository
import javax.inject.Inject

class ObserveBooksUseCase @Inject constructor(
    private val repository: BookRepository
) {
    operator fun invoke() = repository.observeBooks()
}
