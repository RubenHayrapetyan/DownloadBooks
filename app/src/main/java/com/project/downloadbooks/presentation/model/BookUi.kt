package com.project.downloadbooks.presentation.model

import kotlinx.serialization.Serializable

@Serializable
data class BookUi(
  val id: String,
  val imageLink: String,
  val title: String,
  val authors: List<String>,
  val description: String,
)