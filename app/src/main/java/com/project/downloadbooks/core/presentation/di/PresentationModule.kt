package com.project.downloadbooks.core.presentation.di

import com.project.downloadbooks.core.presentation.BooksSharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
  viewModel {
    BooksSharedViewModel(get(), get(), get(), get(), get(), get())
  }
}