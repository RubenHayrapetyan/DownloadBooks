package com.project.downloadbooks.core.presentation.mapper

import com.project.downloadbooks.domain.model.BookModel
import com.project.downloadbooks.presentation.model.BookUi

internal fun BookModel.toBookModel() = BookUi(
  id = this.id,
  title = this.title,
  authors = this.authors,
  description = this.description,
  imageLink = this.fileName,
)