package com.example.redditfortablet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.redditfortablet.model.Entry
import com.example.redditfortablet.model.Feed

class FeedAdapter(val context: Context, val feedList: List<Entry>): RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var feedImage: ImageView
        var feedTitle: TextView
        var feedAuther: TextView
        var feedUpdated: TextView

        init {
            feedImage = itemView.findViewById(R.id.feedImage)
            feedTitle = itemView.findViewById(R.id.feedTitle)
            feedAuther = itemView.findViewById(R.id.feedAuthor)
            feedUpdated = itemView.findViewById(R.id.feedUpdated)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.feed_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.feedTitle.text = feedList[position].post.title.toString()
        holder.feedAuther.text = feedList[position].post.author.toString()
        holder.feedUpdated.text = feedList[position].post.created.toString()
        val image = feedList[position].post.thumbnail
        if (feedList[position].post.thumbnail != "default") {
            Glide.with(context)
                .load(image)
                .into(holder.feedImage)
        } else {
            Glide.with(context)
                .load("https://www.redditinc.com/assets/images/site/reddit-logo.png")
                .into(holder.feedImage)
        }



    }

    override fun getItemCount(): Int {
        return feedList.size
    }
}