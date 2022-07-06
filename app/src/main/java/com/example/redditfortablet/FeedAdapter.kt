package com.example.redditfortablet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.redditfortablet.model.Feed

class FeedAdapter(val context: Context, val feedList: List<Feed>): RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var feedImage: ImageView
        var feedTitle: TextView
        var feedAuther: TextView
        var feedUpdated: TextView
        var feedProgessDialog: ProgressBar

        init {
            feedImage = itemView.findViewById(R.id.feedImage)
            feedTitle = itemView.findViewById(R.id.feedTitle)
            feedAuther = itemView.findViewById(R.id.feedAuthor)
            feedUpdated = itemView.findViewById(R.id.feedUpdated)
            feedProgessDialog = itemView.findViewById(R.id.feedProgressDialog)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.feed_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {



    }

    override fun getItemCount(): Int {
        return feedList.size
    }
}