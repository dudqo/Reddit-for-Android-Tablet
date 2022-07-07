package com.example.redditfortablet.model

import com.google.gson.annotations.SerializedName
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

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