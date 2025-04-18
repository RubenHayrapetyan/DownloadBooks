package com.project.downloadbooks.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.project.downloadbooks.data.model.local.BookEntity

@Database(
  entities = [BookEntity::class],
  version = 1
)
@TypeConverters(Converters::class)
abstract class BooksDatabase: RoomDatabase() {

  abstract val booksDao: BooksDao
}