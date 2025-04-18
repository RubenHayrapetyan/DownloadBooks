package com.project.downloadbooks.presentation.screens.search_books.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.project.downloadbooks.R

@Composable
internal fun ErrorMessage(
  message: String?,
  modifier: Modifier = Modifier,
  onClickRetry: () -> Unit
) {
  Column(modifier = modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
    Text(
      text = message ?: stringResource(id = R.string.error_something_went_wrong),
      color = colorResource(id = R.color.error_red),
      modifier = Modifier.fillMaxWidth(),
      textAlign = TextAlign.Center,
      maxLines = 6
    )
    OutlinedButton(onClick = onClickRetry) {
      Text(text = stringResource(id = R.string.btn_retry))
    }
  }
}