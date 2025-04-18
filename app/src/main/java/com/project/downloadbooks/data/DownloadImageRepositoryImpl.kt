package com.project.downloadbooks.data

import com.project.downloadbooks.domain.repository.DownloadImageRepository
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class DownloadImageRepositoryImpl : DownloadImageRepository {

  override fun invoke(url: String): ByteArray? {
    return try {
      val client = OkHttpClient()
      val request = Request.Builder().url(url).build()
      val response = client.newCall(request).execute()

      if (response.isSuccessful) {
        response.body?.bytes()
      } else {
        null
      }
    } catch (e: IOException) {
      null
    }
  }
}