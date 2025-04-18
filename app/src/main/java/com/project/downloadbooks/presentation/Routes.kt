package com.project.downloadbooks.presentation

import com.project.downloadbooks.presentation.model.BookUi
import kotlinx.serialization.Serializable

sealed interface Routes {
  @Serializable
  data object SearchBooksRoute : Routes

  @Serializable
  data object FavoritesRoute : Routes

  @Serializable
  data class DetailsRoute(
    val bookUi: BookUi,
    val isForOnline: Boolean
  ) : Routes
}