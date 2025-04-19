package com.project.downloadbooks.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.project.downloadbooks.data.remote.BooksApiService
import com.project.downloadbooks.data.util.DataConstants
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
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
    val contentType = "application/json".toMediaType()
    val networkJson = Json { ignoreUnknownKeys = true }
    Retrofit.Builder()
      .baseUrl(DataConstants.BASE_URL)
      .addConverterFactory(networkJson.asConverterFactory(contentType))
      .client(get())
      .build()
  }

  single<BooksApiService> {
    get<Retrofit>().create(BooksApiService::class.java)
  }
}
