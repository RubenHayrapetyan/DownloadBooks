package com.project.downloadbooks.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.project.downloadbooks.data.model.local.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {
  @Upsert
  suspend fun upsert(bookEntity: BookEntity)

  @Query("SELECT * FROM books")
  fun getAllBooks(): Flow<List<BookEntity>>
}