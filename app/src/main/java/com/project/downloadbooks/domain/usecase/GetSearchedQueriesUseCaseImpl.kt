package com.project.downloadbooks.domain.usecase

import com.project.downloadbooks.domain.repository.PreferencesDataStore

class GetSearchedQueriesUseCaseImpl(
  private val preferencesDataStore: PreferencesDataStore
): GetSearchedQueriesUseCase {
  override fun invoke(): List<String> = preferencesDataStore.getSearchedQueries()
}