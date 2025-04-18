package com.project.downloadbooks.app.di

import android.app.Application
import com.project.downloadbooks.data.BooksRepositoryImpl
import com.project.downloadbooks.data.DownloadImageRepositoryImpl
import com.project.downloadbooks.domain.repository.BooksRepository
import com.project.downloadbooks.domain.repository.DownloadImageRepository
import org.koin.dsl.module

val repositoryModule = module {
  single { get<Application>().applicationContext }
  factory<BooksRepository> { BooksRepositoryImpl(get(), get(), get(), get()) }
  factory<DownloadImageRepository> { DownloadImageRepositoryImpl() }
}