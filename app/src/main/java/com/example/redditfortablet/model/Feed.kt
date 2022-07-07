package com.example.redditfortablet.model

import com.google.gson.annotations.SerializedName


data class NewsFeed(
    var data: Feed
)

data class Feed(
    var before: String,
    var after: String?,
    @SerializedName("children")
    var entries: List<Entry>
)

data class Entry(
    @SerializedName("data")
    var post: Post
)

data class Post(
    var author: String,
    var title: String,
    var num_comments: Int,
    var created: Long,
    var thumbnail: String,
    var url: String
)