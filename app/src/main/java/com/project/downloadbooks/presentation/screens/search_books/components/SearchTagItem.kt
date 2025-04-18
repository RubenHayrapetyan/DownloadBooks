package com.project.downloadbooks.presentation.screens.search_books.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun SearchTagItem(
  text: String,
  onTagClick: (String) -> Unit,
) {
  Text(
    modifier = Modifier
      .background(color = Color.Gray, shape = RoundedCornerShape(25))
      .padding(4.dp)
      .clickable { onTagClick(text) },
    color = Color.White,
    text = text
  )
}

@Preview
@Composable
private fun SearchTagPreview() {
  SearchTagItem(text = "Android", onTagClick = {})
}