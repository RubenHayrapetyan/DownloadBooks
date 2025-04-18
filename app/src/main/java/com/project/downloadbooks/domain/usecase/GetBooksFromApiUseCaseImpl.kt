package com.project.downloadbooks.domain.usecase

import androidx.paging.PagingData
import com.project.downloadbooks.domain.model.BookDomain
import com.project.downloadbooks.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow

class GetBooksFromApiUseCaseImpl(
  private val booksRepository: BooksRepository
): GetBooksFromApiUseCase  {
  override operator fun invoke(query: String): Flow<PagingData<BookDomain>> {
    return booksRepository.getBooksFromApi(query).flow
  }
}