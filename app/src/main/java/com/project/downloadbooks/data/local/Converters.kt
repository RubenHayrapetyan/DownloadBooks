package com.project.downloadbooks.data.local

import androidx.room.TypeConverter

class Converters {

  @TypeConverter
  fun fromAuthorList(value: List<String>): String {
    return value.joinToString(separator = ",")
  }

  @TypeConverter
  fun toAuthorList(value: String): List<String> {
    return if (value.isEmpty()) emptyList() else value.split(",")
  }
}