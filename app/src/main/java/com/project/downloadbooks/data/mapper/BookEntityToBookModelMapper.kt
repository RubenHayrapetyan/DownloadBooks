package com.project.downloadbooks.data.mapper

import com.project.downloadbooks.data.model.local.BookEntity
import com.project.downloadbooks.domain.model.BookModel

internal fun BookEntity.toBookModel() = BookModel(
  id = this.id,
  title = this.title,
  fileName = this.fileAbsolutePath,
  authors = this.authors,
  description = this.description,
)