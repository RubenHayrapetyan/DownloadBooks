package com.project.downloadbooks.data.model.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BooksItemDto(
  @SerialName("items")
  val items: List<BookDto>? = null
)