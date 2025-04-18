package com.project.downloadbooks.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import androidx.paging.compose.collectAsLazyPagingItems
import com.project.downloadbooks.core.presentation.BooksSharedViewModel
import com.project.downloadbooks.core.presentation.screen.DetailsScreen
import com.project.downloadbooks.presentation.model.BookUi
import com.project.downloadbooks.presentation.screens.favorites.FavoritesScreen
import com.project.downloadbooks.presentation.screens.search_books.SearchBooksScreen
import org.koin.androidx.compose.koinViewModel
import kotlin.reflect.typeOf

@Composable
internal fun NavGraph(navController: NavHostController) {
  val booksSharedViewModel = koinViewModel<BooksSharedViewModel>()

  NavHost(
    navController = navController,
    startDestination = Routes.SearchBooksRoute
  ) {
    composable<Routes.SearchBooksRoute>{
      val books = booksSharedViewModel.booksState.booksFlow.collectAsLazyPagingItems()
      SearchBooksScreen(
        state = booksSharedViewModel.booksState,
        onEvent = booksSharedViewModel::onSearchBooksEvent,
        navController = navController,
        books = books,
      )
    }
    composable<Routes.FavoritesRoute> {
      FavoritesScreen(
        state = booksSharedViewModel.booksState,
        onEvent = booksSharedViewModel::onSearchBooksEvent,
        navController = navController
      )
    }
    composable<Routes.DetailsRoute>(
      typeMap = mapOf(typeOf<BookUi>() to BookCustomNavType.BookType)
    ) {
      val args = it.toRoute<Routes.DetailsRoute>()
      DetailsScreen(bookUi = args.bookUi, isForOnline = args.isForOnline)
    }
  }
}