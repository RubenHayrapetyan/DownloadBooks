package com.project.downloadbooks.presentation.helper

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.project.downloadbooks.presentation.model.BookUi
import com.project.downloadbooks.presentation.util.PresentationConstants
import kotlinx.serialization.json.Json

internal fun enqueueDownloadWorker(books: List<BookUi>, context: Context) {
  val constraints = Constraints.Builder()
    .setRequiredNetworkType(NetworkType.CONNECTED)
    .build()

  val jsonBooks = Json.encodeToString(books)
  val inputData = workDataOf(PresentationConstants.WORKER_DATA_KEY to jsonBooks)

  val request = OneTimeWorkRequestBuilder<DownloadImagesWorker>()
    .setConstraints(constraints)
    .setInputData(inputData)
    .addTag(PresentationConstants.WORKER_TAG_KEY)
    .build()

  WorkManager.getInstance(context).enqueueUniqueWork(
    PresentationConstants.WORKER_KEY,
    ExistingWorkPolicy.KEEP,
    request
  )
}