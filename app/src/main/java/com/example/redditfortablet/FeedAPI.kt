package com.example.redditfortablet

import com.example.redditfortablet.model.Feed
import retrofit2.Call
import retrofit2.http.GET


// const val REDDIT_URL = "https://www.reddit.com/"

interface FeedAPI {

    @GET(".rss")
    fun getFeed(): Call<Feed>
}