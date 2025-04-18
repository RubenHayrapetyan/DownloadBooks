package com.project.downloadbooks.domain.usecase

import com.project.downloadbooks.domain.model.BookModel
import kotlinx.coroutines.flow.Flow

interface GetBooksFromDbUseCase {
  operator fun invoke(query: String): Flow<List<BookModel>>
}