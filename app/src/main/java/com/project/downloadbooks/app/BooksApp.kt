package com.project.downloadbooks.app

import android.app.Application
import com.project.downloadbooks.app.di.repositoryModule
import com.project.downloadbooks.data.di.sharedPreferencesModule
import com.project.downloadbooks.core.presentation.di.presentationModule
import com.project.downloadbooks.data.di.localModule
import com.project.downloadbooks.data.di.remoteModule
import com.project.downloadbooks.domain.di.useCaseModule
import com.project.downloadbooks.presentation.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BooksApp : Application() {
  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidContext(this@BooksApp)
      modules(
        presentationModule,
        useCaseModule,
        repositoryModule,
        localModule,
        remoteModule,
        sharedPreferencesModule,
        networkModule
      )
    }
  }
}