package com.project.downloadbooks.domain.usecase

interface SaveQueryUseCase {
  operator fun invoke(query: String)
}