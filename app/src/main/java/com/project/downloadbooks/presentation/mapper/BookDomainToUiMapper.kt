package com.project.downloadbooks.presentation.mapper

import com.project.downloadbooks.domain.model.BookDomain
import com.project.downloadbooks.presentation.model.BookUi

internal fun BookDomain.toUi() = BookUi(
  id = this.id,
  imageLink = this.volumeInfo.toUi().imageLink,
  title = this.volumeInfo.toUi().title,
  authors = this.volumeInfo.toUi().authors,
  description = this.volumeInfo.toUi().description,
)