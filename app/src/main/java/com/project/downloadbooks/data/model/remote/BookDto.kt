package com.project.downloadbooks.data.model.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookDto(
  @SerialName("id")
  val id: String? = null,
  @SerialName("volumeInfo")
  val volumeInfo: VolumeInfoDto? = null
)