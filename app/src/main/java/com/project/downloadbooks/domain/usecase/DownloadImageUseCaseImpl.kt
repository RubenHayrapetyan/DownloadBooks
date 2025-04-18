package com.project.downloadbooks.domain.usecase

import com.project.downloadbooks.domain.repository.DownloadImageRepository

class DownloadImageUseCaseImpl(
  private val downloadImageRepository: DownloadImageRepository
): DownloadImageUseCase {
  override fun invoke(url: String): ByteArray? = downloadImageRepository(url)
}