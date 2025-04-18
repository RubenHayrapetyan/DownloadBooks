package com.project.downloadbooks.domain.usecase

import com.project.downloadbooks.domain.model.BookModel

interface SaveImageToStorageUseCase {
  suspend operator fun invoke(bytes: ByteArray, url: String, book: BookModel)
}