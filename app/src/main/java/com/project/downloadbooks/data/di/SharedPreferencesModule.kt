package com.project.downloadbooks.data.di

import android.content.Context
import android.content.SharedPreferences
import com.project.downloadbooks.data.PreferencesDataStoreImpl
import com.project.downloadbooks.data.util.DataConstants
import com.project.downloadbooks.domain.repository.PreferencesDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sharedPreferencesModule = module {
  single<SharedPreferences> {
    androidContext().getSharedPreferences(DataConstants.PREFS_NAME, Context.MODE_PRIVATE)
  }
  single<PreferencesDataStore> { PreferencesDataStoreImpl(get()) }
}