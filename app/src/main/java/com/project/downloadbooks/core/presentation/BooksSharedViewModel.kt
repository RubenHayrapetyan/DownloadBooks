package com.project.downloadbooks.core.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.project.downloadbooks.core.presentation.mapper.toBookModel
import com.project.downloadbooks.domain.usecase.ClearQueriesUseCase
import com.project.downloadbooks.domain.usecase.GetBooksFromApiUseCase
import com.project.downloadbooks.domain.usecase.GetBooksFromDbUseCase
import com.project.downloadbooks.domain.usecase.GetSearchedQueriesUseCase
import com.project.downloadbooks.domain.usecase.SaveQueryUseCase
import com.project.downloadbooks.presentation.helper.ConnectivityObserver
import com.project.downloadbooks.presentation.mapper.toUi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class BooksSharedViewModel(
  private val getBooksFromApiUseCase: GetBooksFromApiUseCase,
  private val saveQueryUseCase: SaveQueryUseCase,
  private val getSearchedQueriesUseCase: GetSearchedQueriesUseCase,
  private val clearQueriesUseCase: ClearQueriesUseCase,
  private val getBooksFromDbUseCase: GetBooksFromDbUseCase,
  private val connectivityObserver: ConnectivityObserver
) : ViewModel() {

  var booksState by mutableStateOf(BooksContract.BooksState())
    private set

  init {
    observeConnectivity()
    booksState = booksState.copy(savedQueries = getSearchedQueriesUseCase())
    searchBook()
  }

  internal fun onSearchBooksEvent(event: BooksContract.BooksEvent) {
    when (event) {
      is BooksContract.BooksEvent.Book -> {
        searchBook(event.searchedQuery)
      }

      is BooksContract.BooksEvent.AddBookToFavorites -> {
        if (booksState.favoriteBooks.contains(event.bookUi)) return

        booksState = booksState.copy(
          favoriteBooks = booksState.favoriteBooks + event.bookUi
        )
      }

      BooksContract.BooksEvent.ClearQueries -> {
        clearQueriesUseCase()
        booksState = booksState.copy(savedQueries = emptyList())
      }

      is BooksContract.BooksEvent.OnQueryChange -> booksState = booksState.copy(
        query = event.query
      )

      BooksContract.BooksEvent.GetBooksFromDb -> {
        viewModelScope.launch {
          getBooksFromDbUseCase(booksState.query)
            .map { it.map { it.toBookModel() } }
            .collectLatest { bookList ->
              booksState = booksState.copy(booksFromDb = bookList)
            }
        }
      }
    }
  }

  private fun searchBook(query: String = "") {
    if (query.isNotEmpty()) {
      saveQueryUseCase(query = query)
    }

    booksState = booksState.copy(
      query = query,
      savedQueries = getSearchedQueriesUseCase()
    )

    val booksFlow = getBooksFromApiUseCase(query.ifEmpty { "a" }) // a is default parameter
      .map { it.map { it.toUi() } }
      .cachedIn(viewModelScope)

    booksState = booksState.copy(booksFlow = booksFlow)
  }

  private fun observeConnectivity() {
    connectivityObserver.observe().onEach { status ->
      booksState = booksState.copy(hasInternet = status == ConnectivityObserver.Status.Available)
    }.launchIn(viewModelScope)
  }
}