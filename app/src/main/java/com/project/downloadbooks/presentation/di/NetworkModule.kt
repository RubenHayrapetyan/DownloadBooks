package com.project.downloadbooks.presentation.di

import android.content.Context
import android.net.ConnectivityManager
import com.project.downloadbooks.presentation.helper.ConnectivityObserver
import com.project.downloadbooks.presentation.helper.NetworkConnectivityObserver
import org.koin.dsl.module

val networkModule = module {
  single<ConnectivityObserver> {
    val context: Context = get()
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    NetworkConnectivityObserver(connectivityManager)
  }
}