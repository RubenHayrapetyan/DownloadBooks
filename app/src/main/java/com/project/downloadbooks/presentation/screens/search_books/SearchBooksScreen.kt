package com.project.downloadbooks.presentation.screens.search_books

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.project.downloadbooks.R
import com.project.downloadbooks.core.presentation.BooksContract
import com.project.downloadbooks.core.presentation.components.BookItem
import com.project.downloadbooks.presentation.Routes
import com.project.downloadbooks.presentation.model.BookUi
import com.project.downloadbooks.presentation.screens.search_books.components.ErrorMessage
import com.project.downloadbooks.presentation.screens.search_books.components.SearchInput
import com.project.downloadbooks.presentation.screens.search_books.components.SearchTagItem

@Composable
internal fun SearchBooksScreen(
  state: BooksContract.BooksState,
  onEvent: (BooksContract.BooksEvent) -> Unit,
  navController: NavController,
  books: LazyPagingItems<BookUi>,
) {
  Column(
    modifier = Modifier
      .padding(horizontal = 16.dp)
      .fillMaxSize()
  ) {
    SearchInput(
      modifier = Modifier.fillMaxWidth(),
      query = state.query,
      onQueryChange = { query ->
        onEvent(BooksContract.BooksEvent.OnQueryChange(query = query))
      },
      onSearchClick = {
        onEvent(BooksContract.BooksEvent.Book(searchedQuery = state.query))
      }
    )

    Spacer(modifier = Modifier.height(8.dp))
    LazyRow(modifier = Modifier.fillMaxWidth()) {
      items(state.savedQueries.size) { index ->
        SearchTagItem(
          text = state.savedQueries[index],
          onTagClick = { query ->
            onEvent(BooksContract.BooksEvent.Book(query))
          }
        )
        Spacer(modifier = Modifier.width(8.dp))
      }
    }

    if (state.savedQueries.isNotEmpty()) {
      Spacer(modifier = Modifier.height(8.dp))
      Text(
        modifier = Modifier.clickable { onEvent(BooksContract.BooksEvent.ClearQueries) },
        text = "Clear history",
        color = Color.Red
      )
    }

    Spacer(modifier = Modifier.height(8.dp))

    LazyColumn(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.spacedBy(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      items(books.itemCount) { index ->
        books[index]?.let { book ->
          BookItem(
            isBookInFavoriteList = book.id in state.favoriteBooks.map { it.id },
            bookUi = book,
            addToFavorites = { onEvent(BooksContract.BooksEvent.AddBookToFavorites(it)) },
            onClick = {
              navController.navigate(
                Routes.DetailsRoute(bookUi = book, isForOnline = true)
              )
            }
          )
        }
      }
      books.apply {
        when {
          loadState.refresh is LoadState.Loading -> {
            item { CircularProgressIndicator() }
          }

          loadState.append is LoadState.Loading -> {
            item {
              Text(text = stringResource(R.string.loading_next_item))
            }
          }

          loadState.append is LoadState.Error -> {
            val error = books.loadState.append as LoadState.Error
            item {
              ErrorMessage(
                modifier = Modifier.fillMaxWidth(),
                message = error.error.localizedMessage,
                onClickRetry = { retry() })
            }
          }

          loadState.refresh is LoadState.Error -> {
            val error = books.loadState.refresh as LoadState.Error
            item {
              ErrorMessage(
                modifier = Modifier.fillMaxWidth(),
                message = error.error.localizedMessage,
                onClickRetry = { retry() })
            }
          }
        }
      }
    }
  }
}