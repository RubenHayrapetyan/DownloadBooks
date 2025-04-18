package com.project.downloadbooks.domain.usecase

interface DownloadImageUseCase {
  operator fun invoke(url: String): ByteArray?
}