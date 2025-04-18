package com.project.downloadbooks.data.model.remote

import com.squareup.moshi.Json

data class ImageLinksDto(
  @field:Json(name = "thumbnail")
  val thumbnail: String
)