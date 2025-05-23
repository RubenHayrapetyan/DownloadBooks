package com.project.downloadbooks.data.remote

import com.project.downloadbooks.data.model.remote.BooksItemDto
import com.project.downloadbooks.data.util.DataConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {
  @GET(DataConstants.PATH_VOLUMES)
  suspend fun searchBooks(
    @Query("q") query: String,
    @Query("startIndex") startIndex: Int,
    @Query("maxResults") maxResults: Int
  ): BooksItemDto
}