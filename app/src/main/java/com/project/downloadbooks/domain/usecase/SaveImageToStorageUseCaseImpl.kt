package com.project.downloadbooks.domain.usecase

import com.project.downloadbooks.domain.model.BookModel
import com.project.downloadbooks.domain.repository.BooksRepository

class SaveImageToStorageUseCaseImpl(
  private val booksRepository: BooksRepository
): SaveImageToStorageUseCase {
  override suspend fun invoke(bytes: ByteArray, url: String, book: BookModel) {
    booksRepository.saveToStorage(bytes, url, book)
  }
}