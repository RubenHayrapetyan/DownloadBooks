package com.project.downloadbooks.core.presentation

import androidx.paging.PagingData
import com.project.downloadbooks.presentation.model.BookUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class BooksContract {
  sealed interface BooksEvent {
    data class OnQueryChange(val query: String) : BooksEvent
    data class Book(val searchedQuery: String) : BooksEvent
    data class AddBookToFavorites(val bookUi: BookUi) : BooksEvent
    data object ClearQueries : BooksEvent
    data object GetBooksFromDb : BooksEvent
  }

  data class BooksState(
    val query: String = "",
    val savedQueries: List<String> = emptyList(),
    val booksFlow: Flow<PagingData<BookUi>> = emptyFlow(),
    val favoriteBooks: List<BookUi> = emptyList(),
    val booksFromDb: List<BookUi> = emptyList(),
    val hasInternet: Boolean = false
  )
}