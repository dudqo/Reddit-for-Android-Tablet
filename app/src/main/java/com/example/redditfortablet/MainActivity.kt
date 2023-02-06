@file:Suppress("DEPRECATION")

package com.example.redditfortablet

import android.os.Bundle
import android.util.Log.d
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.redditfortablet.model.Post
import com.example.redditfortablet.model.NewsFeed
import com.example.redditfortablet.retrofitBuilder.retrofit
import retrofit2.Call
import retrofit2.Callback
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


        val call: Call<NewsFeed> = retrofit.getFeed(searchText)

        call.enqueue(object : Callback<NewsFeed?> {
            override fun onResponse(call: Call<NewsFeed?>, response: Response<NewsFeed?>) {
                val responseBody = response.body()!!

                feedAdapter = FeedAdapter(baseContext, responseBody.data.entries)
                feedAdapter.notifyDataSetChanged()
                recyclerview_feed.adapter = feedAdapter



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


            override fun onFailure(call: Call<NewsFeed?>, t: Throwable) {
                d("mainActivy", "onFailure: " + t.message)
                t.printStackTrace()
            }
        })
    }

}

