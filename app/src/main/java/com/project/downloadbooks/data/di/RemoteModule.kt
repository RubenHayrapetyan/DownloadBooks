package com.project.downloadbooks.data.di

import com.project.downloadbooks.core.util.CoreConstants
import com.project.downloadbooks.data.remote.BooksApiService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val remoteModule = module {

  single<OkHttpClient> {
    OkHttpClient.Builder().apply {
      readTimeout(1, TimeUnit.MINUTES)
      connectTimeout(1, TimeUnit.MINUTES)
      callTimeout(1, TimeUnit.MINUTES)
      writeTimeout(1, TimeUnit.MINUTES)
    }.build()
  }

  single<Retrofit> {
    Retrofit.Builder()
      .baseUrl(CoreConstants.BASE_URL)
      .addConverterFactory(MoshiConverterFactory.create())
      .client(get())
      .build()
  }

  single<BooksApiService> {
    get<Retrofit>().create(BooksApiService::class.java)
  }
}
