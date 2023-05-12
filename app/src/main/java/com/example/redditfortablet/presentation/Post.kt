package com.example.redditfortablet.presentation

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.redditfortablet.R


class Post : Fragment() {

    companion object {
        fun newInstance() = Post()
    }

    private val viewModel: PostViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_post, container, false)

        val postTitleView = view.findViewById<TextView>(R.id.post_title)
        val postContent = view.findViewById<TextView>(R.id.post_content)
        postContent.setMovementMethod(ScrollingMovementMethod())
        val postImage = view.findViewById<ImageView>(R.id.post_image)
        var image_url: String = ""

        val titleObserver = Observer<String> { newTitle ->
            postTitleView.text = newTitle
        }
        val contentObserver = Observer<String> { newContent ->
            postContent.text = newContent
        }
        val imageObserver = Observer<String> { newImage ->
            image_url = newImage
        }

        viewModel.title.observe(viewLifecycleOwner, titleObserver)
        viewModel.content.observe(viewLifecycleOwner, contentObserver)
        viewModel.content.observe(viewLifecycleOwner, imageObserver)


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}