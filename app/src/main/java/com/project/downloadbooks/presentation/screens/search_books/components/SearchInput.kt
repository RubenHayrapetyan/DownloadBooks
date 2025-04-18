package com.project.downloadbooks.presentation.screens.search_books.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.project.downloadbooks.R

@Composable
internal fun SearchInput(
  query: String,
  onQueryChange: (String) -> Unit,
  onSearchClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Row(modifier = modifier.fillMaxWidth()) {
    OutlinedTextField(
      value = query,
      onValueChange = { onQueryChange(it) },
      modifier = Modifier.weight(1f),
      singleLine = true,
      placeholder = { Text(stringResource(R.string.hint_search)) },
      leadingIcon = {
        Icon(
          imageVector = Icons.Default.Search,
          contentDescription = "Search Icon"
        )
      },
      trailingIcon = {
        if (query.isNotEmpty()) {
          IconButton(onClick = { onQueryChange("") }) {
            Icon(
              imageVector = Icons.Default.Close,
              contentDescription = "Clear Text"
            )
          }
        }
      }
    )
    Button(
      onClick = { onSearchClick() },
      content = { Text(stringResource(R.string.btn_search)) }
    )
  }
}


@Preview
@Composable
private fun SearchInputPreview() {
  SearchInput(
    query = "",
    onQueryChange = { },
    onSearchClick = { },
  )
}
