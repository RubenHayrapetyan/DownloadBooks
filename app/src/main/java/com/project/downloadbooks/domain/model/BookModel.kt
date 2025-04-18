package com.project.downloadbooks.domain.model

data class BookModel(
  val id: String,
  val title: String,
  val fileName: String = "",
  val authors: List<String>,
  val description: String,
)