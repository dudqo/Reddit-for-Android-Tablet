package com.example.redditfortablet.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState

class FeedPagingSource(
    private val feedApi: FeedApi
) : PagingSource<Int, Post>() {

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        TODO("Not yet implemented")
    }


}