package com.example.redditfortablet

import android.content.Context
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.redditfortablet.model.Entry
import org.w3c.dom.Text
import java.lang.NullPointerException


class FeedAdapter(val context: Context, val feedList: List<Entry>): RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    lateinit var recyclerViewInterface: RecyclerViewInterface

    fun setOnItemClickListener(listener: RecyclerViewInterface) {
        recyclerViewInterface = listener
    }

    class ViewHolder(itemView: View, listener: RecyclerViewInterface): RecyclerView.ViewHolder(itemView) {
        var feedImage: ImageView
        var feedTitle: TextView
        var feedAuther: TextView
        var feedUpdated: TextView
        var feedSubreddit: TextView

        init {
            feedImage = itemView.findViewById(R.id.feedImage)
            feedTitle = itemView.findViewById(R.id.feedTitle)
            feedAuther = itemView.findViewById(R.id.feedAuthor)
            feedUpdated = itemView.findViewById(R.id.feedUpdated)
            feedSubreddit = itemView.findViewById(R.id.feedSubreddit)

            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.feed_layout, parent, false)
        return ViewHolder(itemView, recyclerViewInterface)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.feedTitle.text = feedList[position].post.title
        holder.feedAuther.text = feedList[position].post.author
        holder.feedUpdated.text = java.time.format.DateTimeFormatter.ISO_INSTANT
            .format(java.time.Instant.ofEpochSecond(feedList[position].post.created))
        holder.feedSubreddit.text = "Posted on: " + feedList[position].post.subreddit
        val image = feedList[position].post.thumbnail

        if (Patterns.WEB_URL.matcher(image).matches()) {
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