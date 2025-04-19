package com.project.downloadbooks.data.model.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageLinksDto(
  @SerialName("thumbnail")
  val thumbnail: String? = null
)