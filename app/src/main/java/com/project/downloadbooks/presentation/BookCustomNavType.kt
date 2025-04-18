package com.project.downloadbooks.presentation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.project.downloadbooks.presentation.model.BookUi
import kotlinx.serialization.json.Json

object BookCustomNavType {
  val BookType = object : NavType<BookUi>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): BookUi? {
      return Json.decodeFromString(bundle.getString(key) ?: return null)
    }

    override fun parseValue(value: String): BookUi {
      return Json.decodeFromString(Uri.decode(value))
    }

    override fun serializeAsValue(value: BookUi): String {
      return Uri.encode(Json.encodeToString(value))
    }

    override fun put(bundle: Bundle, key: String, value: BookUi) {
      bundle.putString(key, Json.encodeToString(value))
    }

  }
}