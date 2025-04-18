package com.project.downloadbooks.data

import android.app.Application
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.project.downloadbooks.data.local.BooksDao
import com.project.downloadbooks.data.mapper.toBookModel
import com.project.downloadbooks.data.model.local.BookEntity
import com.project.downloadbooks.data.remote.BooksApiService
import com.project.downloadbooks.data.remote.BooksPagingSource
import com.project.downloadbooks.domain.model.BookDomain
import com.project.downloadbooks.domain.model.BookModel
import com.project.downloadbooks.domain.repository.BooksRepository
import com.project.downloadbooks.domain.repository.PreferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.File
import java.util.UUID

class BooksRepositoryImpl(
  private val api: BooksApiService,
  private val preferencesDataStore: PreferencesDataStore,
  private val application: Application,
  private val booksDao: BooksDao
) : BooksRepository {

  override fun getBooksFromApi(query: String): Pager<Int, BookDomain> {
    return Pager(
      config = PagingConfig(pageSize = 10),
      pagingSourceFactory = { BooksPagingSource(api, query) }
    )
  }

  override fun getBooksFromDb(): Flow<List<BookModel>> =
    booksDao.getAllBooks().map { it.map { it.toBookModel() } }

  override suspend fun saveToStorage(bytes: ByteArray, url: String, book: BookModel) {
    preferencesDataStore.saveDownloadedImgUrl(url)

    val guessedExtension = when {
      url.endsWith(".jpeg", true) || url.contains(".jpeg") -> "jpeg"
      url.endsWith(".png", true) || url.contains(".png") -> "png"
      url.endsWith(".webp", true) || url.contains(".webp") -> "webp"
      else -> "jpg"
    }

    val fileName = "${UUID.randomUUID()}.$guessedExtension"
    val file = File(application.applicationContext.filesDir, fileName)
    file.writeBytes(bytes)
    booksDao.upsert(
      bookEntity = BookEntity(
        id = book.id,
        fileAbsolutePath = file.absolutePath,
        authors = book.authors,
        description = book.description,
        title = book.title
      )
    )
  }
}