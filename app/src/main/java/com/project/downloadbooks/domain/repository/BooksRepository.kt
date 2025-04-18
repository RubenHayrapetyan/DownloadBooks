package com.project.downloadbooks.domain.repository

import androidx.paging.Pager
import com.project.downloadbooks.domain.model.BookDomain
import com.project.downloadbooks.domain.model.BookModel
import kotlinx.coroutines.flow.Flow

interface BooksRepository {
  fun getBooksFromApi(query: String): Pager<Int, BookDomain>
  fun getBooksFromDb(): Flow<List<BookModel>>
  suspend fun saveToStorage(bytes: ByteArray, url: String, book: BookModel)
}