package com.project.downloadbooks.core.presentation.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.project.downloadbooks.R
import com.project.downloadbooks.presentation.model.BookUi

@Composable
internal fun BookItem(
  modifier: Modifier = Modifier,
  isForOnline: Boolean = true,
  isForFavoritesUi: Boolean = false,
  isBookInFavoriteList: Boolean = false,
  bookUi: BookUi,
  onClick: () -> Unit = {},
  addToFavorites: (BookUi) -> Unit = {}
) {
  var isFavoriteEnabled by remember { mutableStateOf(!isBookInFavoriteList) }

  Column(modifier = modifier.fillMaxWidth().clickable { onClick() }) {
    Row {
      if (bookUi.imageLink.isNotEmpty() && isForOnline) {
        AsyncImage(
          modifier = Modifier.size(100.dp),
          model = bookUi.imageLink,
          contentDescription = "Book cover",
          error = painterResource(R.drawable.no_book_cover)
        )
      }
      if (!isForOnline && bookUi.imageLink.isNotEmpty()) {
        val bitmap = remember(bookUi.imageLink) { BitmapFactory.decodeFile(bookUi.imageLink) }
        bitmap?.let {
          Image(
            modifier = Modifier.size(100.dp),
            bitmap = it.asImageBitmap(),
            contentDescription = "Book cover",
          )
        }
      }
      Column {
        val title = bookUi.title
        val authors = bookUi.authors
        if (title.isNotEmpty()) {
          Text(text = bookUi.title)
        }
        if (authors.isNotEmpty()) {
          authors.forEach {
            Text(text = it, color = colorResource(R.color.teal_700))
          }
        }
      }
    }

    if (!isForFavoritesUi) {
      Button(
        modifier = Modifier.fillMaxWidth(),
        enabled = isFavoriteEnabled,
        onClick = {
          isFavoriteEnabled = false
          addToFavorites(bookUi)
        },
        content = {
          Text("Add to favorites")
        }
      )
    }

    Spacer(modifier = Modifier.height(8.dp))
    HorizontalDivider(
      thickness = 1.dp,
      color = Color.Black
    )
  }
}

@Preview
@Composable
private fun BookItemPreview() {
  BookItem(
    bookUi = BookUi(
      id = "123",
      title = "Title",
      authors = listOf("Author 1", "Author 2"),
      imageLink = "http://books.google.com/books/content?id=qDMQEQAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api",
      description = ""
    ),
    addToFavorites = { },
  )
}