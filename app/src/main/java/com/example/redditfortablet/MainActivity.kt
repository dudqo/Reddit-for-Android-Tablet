@file:Suppress("DEPRECATION")

package com.example.redditfortablet

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.redditfortablet.model.NewsFeed
import com.example.redditfortablet.retrofitBuilder.retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var feedAdapter: FeedAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    private var searchText = "all"
    private val postViewModel: PostViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerview_feed = findViewById<RecyclerView>(R.id.recyclerview_feed)
        val searchView = findViewById<SearchView>(R.id.searchView)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerview_feed.layoutManager = linearLayoutManager

        run_retrofit()

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                searchText = query


                run_retrofit()
                return false
            }
        })

    }

    private fun run_retrofit() {

        val recyclerview_feed = findViewById<RecyclerView>(R.id.recyclerview_feed)


        GlobalScope.launch(Dispatchers.IO) {
            val call: Response<NewsFeed> = retrofit.getFeed(searchText)
            val responseBody = call.body()!!

            feedAdapter = FeedAdapter(baseContext, responseBody.data.entries)
            feedAdapter.notifyDataSetChanged()


            runOnUiThread {
                recyclerview_feed.adapter = feedAdapter
            }
            feedAdapter.setOnItemClickListener(object: RecyclerViewInterface {
                override fun onItemClick(position: Int) {
                    postViewModel.setPostPosition(position)
                    postViewModel.setContent(responseBody.data.entries.get(position).post.contentText.toString())
                    postViewModel.setTitle(responseBody.data.entries.get(position).post.title)
                    postViewModel.setImage(responseBody.data.entries.get(position).post.thumbnail)


                    val fragmentManger = supportFragmentManager
                    val postFragment: Fragment = Post()
                    val fragmentTransaction = fragmentManger.beginTransaction()
                    fragmentTransaction.add(R.id.fragmentContainerView, postFragment)
                    fragmentTransaction.commit()
                }
            })
        }


    }

}

