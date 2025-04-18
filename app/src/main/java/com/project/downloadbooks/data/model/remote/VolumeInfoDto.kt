package com.project.downloadbooks.data.model.remote

import com.squareup.moshi.Json

data class VolumeInfoDto(
  @field:Json(name = "title")
  val title: String,
  @field:Json(name = "authors")
  val authors: List<String>?,
  @field:Json(name = "description")
  val description: String,
  @field:Json(name = "imageLinks")
  val imageLinksDto: ImageLinksDto?
)