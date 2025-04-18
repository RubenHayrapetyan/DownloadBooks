package com.project.downloadbooks.domain.usecase

import com.project.downloadbooks.domain.model.BookModel
import com.project.downloadbooks.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow

class GetBooksFromDbUseCaseImpl(
  private val booksRepository: BooksRepository
):GetBooksFromDbUseCase {
  override fun invoke(query: String): Flow<List<BookModel>> = booksRepository.getBooksFromDb()
}