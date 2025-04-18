package com.project.downloadbooks.core.presentation.screen

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.project.downloadbooks.R
import com.project.downloadbooks.presentation.model.BookUi

@Composable
internal fun DetailsScreen(
  modifier: Modifier = Modifier,
  bookUi: BookUi,
  isForOnline: Boolean,
) {
  Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
    if (bookUi.imageLink.isNotEmpty() && isForOnline) {
      AsyncImage(
        modifier = Modifier.size(200.dp),
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
      val description = bookUi.description

      if (title.isNotEmpty()) {
        Text(
          text = title,
          style = TextStyle(fontWeight = FontWeight.W700),
        )
      }
      if (authors.isNotEmpty()) {
        authors.forEach {
          Text(text = it, color = colorResource(R.color.teal_700))
        }
      }
      Text(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        text = description.ifEmpty { stringResource(R.string.no_description) },
        color = colorResource(R.color.purple_200)
      )
    }
  }
}