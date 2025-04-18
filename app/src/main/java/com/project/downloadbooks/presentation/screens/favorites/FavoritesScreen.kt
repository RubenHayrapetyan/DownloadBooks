package com.project.downloadbooks.presentation.screens.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.downloadbooks.R
import com.project.downloadbooks.core.presentation.components.BookItem
import com.project.downloadbooks.presentation.Routes
import com.project.downloadbooks.presentation.helper.enqueueDownloadWorker
import com.project.downloadbooks.core.presentation.BooksContract

@Composable
internal fun FavoritesScreen(
  state: BooksContract.BooksState,
  onEvent: (BooksContract.BooksEvent) -> Unit,
  navController: NavController
) {
  Column(
    modifier = Modifier
      .padding(horizontal = 16.dp)
      .fillMaxSize()
  ) {
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxWidth()) {
      Button(
        modifier = Modifier.align(Alignment.Center),
        content = {
          Text(stringResource(R.string.btn_download_all))
        },
        onClick = {
          enqueueDownloadWorker(
            books = state.favoriteBooks,
            context = context
          )
        }
      )
    }

    Spacer(modifier = Modifier.height(8.dp))

    val books = if (state.hasInternet) {
      state.favoriteBooks
    } else {
      onEvent(BooksContract.BooksEvent.GetBooksFromDb)
      state.booksFromDb
    }



    LazyColumn(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      items(
        items = books,
        key = { item -> item.id }
      ) { book ->
        BookItem(
          bookUi = book,
          isForFavoritesUi = true,
          isForOnline = state.hasInternet,
          onClick = {
            navController.navigate(
              Routes.DetailsRoute(bookUi = book, isForOnline = state.hasInternet)
            )
          }
        )
      }
    }
  }
}