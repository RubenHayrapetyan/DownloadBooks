package com.project.downloadbooks.domain.usecase

interface GetSearchedQueriesUseCase {
  operator fun invoke(): List<String>
}