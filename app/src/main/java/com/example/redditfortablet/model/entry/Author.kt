package com.example.redditfortablet.model.entry

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "author", strict = false)
data class Author(
    @field:Element(name = "userid", required = false) @param:Element(name = "userid")
    val userid: String,
    @field:Element(name = "uri", required = false) @param:Element(name = "uri")
    val uri: String
)
