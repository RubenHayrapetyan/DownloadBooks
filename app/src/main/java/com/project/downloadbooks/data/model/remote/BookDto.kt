package com.project.downloadbooks.data.model.remote

import com.squareup.moshi.Json

data class BookDto(
  @field:Json(name = "id")
  val id: String,
  @field:Json(name = "volumeInfo")
  val volumeInfo: VolumeInfoDto?
)