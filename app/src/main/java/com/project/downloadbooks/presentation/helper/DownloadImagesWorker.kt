package com.project.downloadbooks.presentation.helper

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.project.downloadbooks.domain.usecase.DownloadImageUseCase
import com.project.downloadbooks.domain.usecase.SaveImageToStorageUseCase
import com.project.downloadbooks.presentation.mapper.toBookModel
import com.project.downloadbooks.presentation.model.BookUi
import com.project.downloadbooks.presentation.util.PresentationConstants
import kotlinx.serialization.json.Json
import org.koin.java.KoinJavaComponent.getKoin

class DownloadImagesWorker(
  context: Context,
  workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

  private val downloadImageUseCase: DownloadImageUseCase by lazy { getKoin().get() }
  private val saveToStorageUseCase: SaveImageToStorageUseCase by lazy { getKoin().get() }

  override suspend fun doWork(): Result {
    val jsonString = inputData.getString(PresentationConstants.WORKER_DATA_KEY)
      ?: return Result.failure()

    val bookList: List<BookUi> = try {
      Json.decodeFromString(jsonString)
    } catch (e: Exception) {
      e.printStackTrace()
      return Result.failure()
    }

    var failed = false

    bookList.forEach { book ->
      val url = book.imageLink

      try {
        downloadImageUseCase(url)?.let {
          saveToStorageUseCase(bytes = it, url = url, book = book.toBookModel())
        }
      } catch (e: Exception) {
        e.printStackTrace()
        failed = true
      }
    }

    return if (failed) Result.retry() else Result.success()
  }
}