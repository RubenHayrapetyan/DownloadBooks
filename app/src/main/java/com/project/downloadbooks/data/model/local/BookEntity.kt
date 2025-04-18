package com.project.downloadbooks.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.downloadbooks.data.util.DataConstants

@Entity(tableName = DataConstants.BOOKS_TABLE)
data class BookEntity(
  @PrimaryKey
  val id: String,
  val fileAbsolutePath: String,
  val title: String,
  val authors: List<String>,
  val description: String,
)