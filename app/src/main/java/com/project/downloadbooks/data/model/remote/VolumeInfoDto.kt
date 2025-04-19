package com.project.downloadbooks.data.model.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VolumeInfoDto(
  @SerialName("title")
  val title: String? = null,
  @SerialName("authors")
  val authors: List<String>? = null,
  @SerialName("description")
  val description: String? = null,
  @SerialName("imageLinks")
  val imageLinksDto: ImageLinksDto? = null
)