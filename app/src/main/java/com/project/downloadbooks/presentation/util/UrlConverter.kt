package com.project.downloadbooks.presentation.util

fun String?.toHttps(): String {
  return this?.replaceFirst("http://", "https://") ?: ""
}