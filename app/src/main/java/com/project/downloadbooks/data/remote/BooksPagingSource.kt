package com.project.downloadbooks.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.project.downloadbooks.data.mapper.toDomain
import com.project.downloadbooks.domain.model.BookDomain

class BooksPagingSource(
  private val api: BooksApiService,
  private val query: String
) : PagingSource<Int, BookDomain>() {

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookDomain> {
    val startIndex = params.key ?: 0
    val maxResults = params.loadSize

    return try {
      val response = api.searchBooks(query, startIndex, maxResults)
      val books = response.items?.map { it.toDomain() } ?: emptyList()

      LoadResult.Page(
        data = books,
        prevKey = if (startIndex == 0) null else startIndex - maxResults,
        nextKey = if (books.isEmpty()) null else startIndex + maxResults
      )
    } catch (e: Exception) {
      LoadResult.Error(e)
    }
  }

  override fun getRefreshKey(state: PagingState<Int, BookDomain>): Int? {
    return state.anchorPosition?.let { anchor ->
      val anchorPage = state.closestPageToPosition(anchor)
      anchorPage?.prevKey?.plus(10) ?: anchorPage?.nextKey?.minus(10)
    }
  }
}