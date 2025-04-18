package com.project.downloadbooks.presentation.helper

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
  fun observe(): Flow<Status>

  enum class Status {
    Available,
    Lost
  }
}