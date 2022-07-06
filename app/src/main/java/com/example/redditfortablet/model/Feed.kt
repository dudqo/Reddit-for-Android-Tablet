package com.example.redditfortablet.model

import com.example.redditfortablet.model.entry.Entry
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "feed", strict = false)
data class Feed(
    @field:Element(name = "category", required = false) @param:Element(name = "category")
    val category: String,
    @field:Element(name = "icon", required = false) @param:Element(name = "icon")
    var icon: String,
    @field:Element(name = "id", required = false) @param:Element(name = "id")
    var id: String,
    @field:Element(name = "link", required = false) @param:Element(name = "link")
    var link: String,
    @field:Element(name = "logo", required = false) @param:Element(name = "logo")
    var logo: String,
    @field:Element(name = "title", required = false) @param:Element(name = "title")
    var title: String,
    @field:Element(name = "updated", required = false) @param:Element(name = "updated")
    var updated: String,
    @field:Element(name = "subtitle", required = false) @param:Element(name = "subtitle")
    var subtitle: String,
    @field:ElementList(name = "entry", required = false) @param:ElementList(name = "entry")
    var entrys: List<Entry>
)