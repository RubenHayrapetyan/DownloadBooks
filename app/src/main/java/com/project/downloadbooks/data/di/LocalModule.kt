package com.project.downloadbooks.data.di

import android.content.Context
import androidx.room.Room
import com.project.downloadbooks.data.local.BooksDao
import com.project.downloadbooks.data.local.BooksDatabase
import com.project.downloadbooks.data.util.DataConstants
import org.koin.dsl.module

val localModule = module {

  single<BooksDatabase> {
    Room
      .databaseBuilder(
        get<Context>(),
        BooksDatabase::class.java,
        DataConstants.DB_NAME
      )
      .build()
  }

  factory<BooksDao> {
    get<BooksDatabase>().booksDao
  }
}
