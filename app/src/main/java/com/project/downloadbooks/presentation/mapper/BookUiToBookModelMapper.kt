package com.project.downloadbooks.presentation.mapper

import com.project.downloadbooks.domain.model.BookModel
import com.project.downloadbooks.presentation.model.BookUi

internal fun BookUi.toBookModel() = BookModel(
  id = this.id,
  title = this.title,
  authors = this.authors,
  description = this.description
)