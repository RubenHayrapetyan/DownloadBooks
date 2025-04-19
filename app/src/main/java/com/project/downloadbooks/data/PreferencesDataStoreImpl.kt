package com.project.downloadbooks.data

import android.content.SharedPreferences
import com.project.downloadbooks.data.util.DataConstants
import com.project.downloadbooks.domain.repository.PreferencesDataStore

class PreferencesDataStoreImpl(private val sharedPreferences: SharedPreferences) :
  PreferencesDataStore {
  override fun saveDownloadedImgUrl(url: String) {
    val existingUrls =
      sharedPreferences.getStringSet(DataConstants.DOWNLOADED_URL_KEY, emptySet())?.toMutableSet()
        ?: mutableSetOf()
    existingUrls.add(url)
    sharedPreferences.edit().putStringSet(DataConstants.DOWNLOADED_URL_KEY, existingUrls).apply()
  }

  override fun getDownloadedImgUrls(): Set<String> =
    sharedPreferences.getStringSet(DataConstants.DOWNLOADED_URL_KEY, emptySet()) ?: emptySet()

  override fun saveSearchedQuery(query: String) {
    val existingQueries =
      sharedPreferences.getStringSet(DataConstants.QUERY_KEY, emptySet())?.toMutableSet()
        ?: mutableSetOf()
    existingQueries.add(query)
    sharedPreferences.edit().putStringSet(DataConstants.QUERY_KEY, existingQueries).apply()
  }

  override fun getSearchedQueries(): List<String> =
    sharedPreferences.getStringSet(DataConstants.QUERY_KEY, emptySet())?.toList() ?: emptyList()

  override fun clearQueries() {
    sharedPreferences.edit().remove(DataConstants.QUERY_KEY).apply()
  }
}