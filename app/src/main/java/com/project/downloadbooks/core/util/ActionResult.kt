package com.project.downloadbooks.core.util

sealed class ActionResult<out T> {
  data class Success<out T>(val data: T) : ActionResult<T>()
  data class Error(val e: Throwable) : ActionResult<Nothing>()
}