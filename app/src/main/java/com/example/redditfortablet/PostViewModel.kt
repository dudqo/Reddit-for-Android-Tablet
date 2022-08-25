package com.example.redditfortablet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PostViewModel : ViewModel() {


    private val postPosition = MutableLiveData<Int>()
    private val content = MutableLiveData<String>()
    private val title = MutableLiveData<String>()
    private val comments: MutableLiveData<List<String>> = MutableLiveData()

    fun setPostPosition (newPosition: Int) {
        postPosition.value = newPosition
    }

    fun setContent (newContent: String) {
        content.value = newContent
    }

    fun setTitle (newTitle: String) {
        title.value = newTitle
    }

    fun setComments (newComments: List<String>) {
        comments.value = newComments
    }

}