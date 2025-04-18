package com.project.downloadbooks.domain.repository

interface DownloadImageRepository {
  operator fun invoke(url: String): ByteArray?
}