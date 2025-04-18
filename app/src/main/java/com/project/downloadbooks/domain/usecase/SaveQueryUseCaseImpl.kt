package com.project.downloadbooks.domain.usecase

import com.project.downloadbooks.domain.repository.PreferencesDataStore

class SaveQueryUseCaseImpl(
  private val preferencesDataStore: PreferencesDataStore
): SaveQueryUseCase {
  override fun invoke(query: String) {
    preferencesDataStore.saveSearchedQuery(query)
  }
}