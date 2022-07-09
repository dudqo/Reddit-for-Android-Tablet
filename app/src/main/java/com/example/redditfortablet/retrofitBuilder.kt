package com.example.redditfortablet

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitBuilder {

    const val REDDIT_URL = "https://www.reddit.com/r/"

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(REDDIT_URL)
        .build()
        .create(FeedAPI::class.java)
}