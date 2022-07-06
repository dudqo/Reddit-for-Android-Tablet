package com.example.redditfortablet.model.entry

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root

@Root(name = "entry", strict = false)
data class Entry(
    @field:Element(name = "content", required = false) @param:Element(name = "content")
    var content: String = "",
    @field:Element(name = "author", required = false) @param:Element(name = "author")
    var author: String = "",
    //@field:Element(name = "id", required = false) @param:Element(name = "id")
    //var id: String,
    @field:Element(name = "title", required = false) @param:Element(name = "title")
    var title: String = "",
    @field:Element(name = "updated", required = false) @param:Element(name = "updated")
    var updated: String = ""
    //@field:Element(name = "category", required = false) @param:Element(name = "category")
    //var category: String,
    // @field:Element(name = "thumbnail", required = false) @param:Element(name = "thumbnail")
    // var thumbnail: String,
    //@field:Element(name = "link", required = false) @param:Element(name = "link")
    //var link: String,
    //@field:Element(name = "published", required = false) @param:Element(name = "published")
    //var published: String

)