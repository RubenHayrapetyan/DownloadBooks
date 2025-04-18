package com.project.downloadbooks.domain.di

import com.project.downloadbooks.domain.usecase.ClearQueriesUseCase
import com.project.downloadbooks.domain.usecase.ClearQueriesUseCaseImpl
import com.project.downloadbooks.domain.usecase.DownloadImageUseCase
import com.project.downloadbooks.domain.usecase.DownloadImageUseCaseImpl
import com.project.downloadbooks.domain.usecase.GetBooksFromApiUseCase
import com.project.downloadbooks.domain.usecase.GetBooksFromApiUseCaseImpl
import com.project.downloadbooks.domain.usecase.GetBooksFromDbUseCase
import com.project.downloadbooks.domain.usecase.GetBooksFromDbUseCaseImpl
import com.project.downloadbooks.domain.usecase.GetSearchedQueriesUseCase
import com.project.downloadbooks.domain.usecase.GetSearchedQueriesUseCaseImpl
import com.project.downloadbooks.domain.usecase.SaveImageToStorageUseCase
import com.project.downloadbooks.domain.usecase.SaveImageToStorageUseCaseImpl
import com.project.downloadbooks.domain.usecase.SaveQueryUseCase
import com.project.downloadbooks.domain.usecase.SaveQueryUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
  factory<GetBooksFromApiUseCase> { GetBooksFromApiUseCaseImpl(get()) }
  factory<SaveQueryUseCase> { SaveQueryUseCaseImpl(get()) }
  factory<GetSearchedQueriesUseCase> { GetSearchedQueriesUseCaseImpl(get()) }
  factory<ClearQueriesUseCase> { ClearQueriesUseCaseImpl(get()) }
  factory<DownloadImageUseCase> { DownloadImageUseCaseImpl(get()) }
  factory<SaveImageToStorageUseCase> { SaveImageToStorageUseCaseImpl(get()) }
  factory<GetBooksFromDbUseCase> { GetBooksFromDbUseCaseImpl(get()) }
}