package com.project.downloadbooks.domain.usecase

import androidx.paging.PagingData
import com.project.downloadbooks.domain.model.BookDomain
import kotlinx.coroutines.flow.Flow

interface GetBooksFromApiUseCase {
  operator fun invoke(query: String): Flow<PagingData<BookDomain>>
}