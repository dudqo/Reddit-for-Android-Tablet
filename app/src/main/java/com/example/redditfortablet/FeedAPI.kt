package com.example.redditfortablet

import com.example.redditfortablet.model.NewsFeed
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


// const val REDDIT_URL = "https://www.reddit.com/r/"

interface FeedAPI {

    @GET("{subreddit}.json")
    fun getFeed(@Path("subreddit") subreddit: String): Call<NewsFeed>
}