@file:Suppress("DEPRECATION")

package com.example.redditfortablet

import android.os.Bundle
import android.util.Log.d
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.redditfortablet.model.Post
import com.example.redditfortablet.model.NewsFeed
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.recyclerview.widget.RecyclerView.OnScrollListener as OnScrollListener


const val REDDIT_URL = "https://www.reddit.com/r/"

class MainActivity : AppCompatActivity() {

    lateinit var feedAdapter: FeedAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    val fragmentManger = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerview_feed = findViewById<RecyclerView>(R.id.recyclerview_feed)
        val textViewContent = findViewById<TextView>(R.id.textViewContent)
        val searchView = findViewById<SearchView>(R.id.searchView)


        linearLayoutManager = LinearLayoutManager(this)
        recyclerview_feed.layoutManager = linearLayoutManager


        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(REDDIT_URL)
            .build()
            .create(FeedAPI::class.java)

        val call: Call<NewsFeed> = retrofit.getFeed()

        call.enqueue(object : Callback<NewsFeed?> {
            override fun onResponse(call: Call<NewsFeed?>, response: Response<NewsFeed?>) {
                val responseBody = response.body()!!

                feedAdapter = FeedAdapter(baseContext, responseBody.data.entries)
                feedAdapter.notifyDataSetChanged()
                recyclerview_feed.adapter = feedAdapter



                feedAdapter.setOnItemClickListener(object: RecyclerViewInterface {
                    override fun onItemClick(position: Int) {
                        textViewContent.text = responseBody.data.entries.get(position).post.contentText
                        val fragmentTransaction = fragmentManger.beginTransaction()

                    }

                })


            }


            override fun onFailure(call: Call<NewsFeed?>, t: Throwable) {
                d("mainActivy", "onFailure: " + t.message)
                t.printStackTrace()
            }
        })



    }

}

