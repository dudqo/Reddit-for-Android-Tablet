package com.example.redditfortablet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PostViewModel : ViewModel() {


    val postPosition = MutableLiveData<Int>()
    val content = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val image = MutableLiveData<String>()
    val comments: MutableLiveData<List<String>> = MutableLiveData()

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

    fun setImage (newImage: String) {
        image.value = newImage
    }

}