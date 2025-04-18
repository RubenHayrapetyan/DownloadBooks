package com.project.downloadbooks.presentation.model

import kotlinx.serialization.Serializable

@Serializable
data class VolumeInfoUi(
  val title: String = "",
  val authors: List<String> = emptyList(),
  val description: String = "",
  val imageLink: String = ""
)