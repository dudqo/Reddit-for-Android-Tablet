@file:Suppress("DEPRECATION")

package com.example.redditfortablet

import android.os.Bundle
import android.util.Log.d
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.redditfortablet.model.Feed
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory


const val REDDIT_URL = "https://www.reddit.com/"

class MainActivity : AppCompatActivity() {

    lateinit var feedAdapter: FeedAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerview_feed = findViewById<RecyclerView>(R.id.recyclerview_feed)
        val textViewTesting = findViewById<TextView>(R.id.textViewTesting)


        linearLayoutManager = LinearLayoutManager(this)
        recyclerview_feed.layoutManager = linearLayoutManager

        val retrofit = Retrofit.Builder()
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .baseUrl(REDDIT_URL)
            .build()


        val feedAPI: FeedAPI = retrofit.create(FeedAPI::class.java)

        val call: Call<Feed> = feedAPI.getFeed()

        call.enqueue(object : Callback<Feed?> {
            override fun onResponse(call: Call<Feed?>, response: Response<Feed?>) {


                textViewTesting.text = response.body()!!.title

                d("mainActivity", "onResponse: feed: " + response.body()!!.title);
                d("mainActivity", "onResponse: Server Response: " + response.toString());

            }

            // feedAdapter = FeedAdapter(baseContext, responseBody)
            // feedAdapter.notifyDataSetChanged()
            // recyclerview_feed.adapter = feedAdapter

            override fun onFailure(call: Call<Feed?>, t: Throwable) {
                d("mainActivy", "onFailure: " + t.message)
            }
        })



    }

}

