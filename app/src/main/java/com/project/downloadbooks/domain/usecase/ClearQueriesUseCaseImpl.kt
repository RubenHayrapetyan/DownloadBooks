package com.project.downloadbooks.domain.usecase

import com.project.downloadbooks.domain.repository.PreferencesDataStore

class ClearQueriesUseCaseImpl(
  private val preferencesDataStore: PreferencesDataStore
): ClearQueriesUseCase {
  override fun invoke() {
    preferencesDataStore.clearQueries()
  }
}