package com.project.downloadbooks.data.model.remote

import com.squareup.moshi.Json

data class BooksItemDto(
  @field:Json(name = "items")
  val items: List<BookDto>?
)