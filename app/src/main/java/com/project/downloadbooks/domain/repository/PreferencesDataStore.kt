package com.project.downloadbooks.domain.repository

interface PreferencesDataStore {
  fun saveDownloadedImgUrl(url: String)
  fun getDownloadedImgUrls(): Set<String>

  fun saveSearchedQuery(query: String)
  fun getSearchedQueries(): List<String>

  fun clearQueries()
}