package com.example.redditfortablet.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface FeedApi {

    @GET("{category}.json")
    suspend fun getListings(@Path("category") category: String): Call<NewsFeed>

    companion object {
        const val BASE_URL = "https://www.reddit.com/api/v1/"
    }
}