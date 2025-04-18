package com.project.downloadbooks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.project.downloadbooks.presentation.BottomNavItem
import com.project.downloadbooks.presentation.NavGraph
import com.project.downloadbooks.presentation.Routes
import com.project.downloadbooks.ui.theme.DownloadBooksTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      DownloadBooksTheme {
        val navController = rememberNavController()
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = currentBackStackEntry?.destination?.route?.substringAfterLast(".")
        val details = "${Routes.DetailsRoute::class.simpleName}/{bookUi}/{isForOnline}"

        Scaffold(modifier = Modifier.fillMaxSize(),
          bottomBar = {
            if (currentRoute != details) {
              BottomBar(navController)
            }
          },
          content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
              NavGraph(navController)
            }
          })
      }
    }
  }
}

@Composable
private fun BottomBar(navController: NavController) {
  val currentBackStackEntry by navController.currentBackStackEntryAsState()
  val currentRoute = currentBackStackEntry?.destination?.route?.substringAfterLast(".")

  val bottomNavItems = listOf(
    BottomNavItem(
      title = stringResource(R.string.search_books),
      icon = Icons.Default.Search,
      route = Routes.SearchBooksRoute
    ),
    BottomNavItem(
      title = stringResource(R.string.favorites),
      icon = Icons.Default.Favorite,
      route = Routes.FavoritesRoute
    )
  )

  NavigationBar(containerColor = Color.White) {
    bottomNavItems.forEach { item ->
      val selected = currentRoute == item.route.toString()

      NavigationBarItem(
        selected = selected,
        onClick = {
          if (navController.currentDestination?.route != item.route.toString()) {
            navController.navigate(item.route) {
              launchSingleTop = true
              restoreState = true
              popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
              }
            }
          }
        },
        icon = {
          Icon(imageVector = item.icon, contentDescription = item.title)
        },
        label = {
          Text(text = item.title, color = if (selected) Color.Black else Color.Gray)
        },
        colors = NavigationBarItemDefaults.colors(
          selectedIconColor = MaterialTheme.colorScheme.surface,
          indicatorColor = MaterialTheme.colorScheme.primary
        )
      )
    }
  }
}
