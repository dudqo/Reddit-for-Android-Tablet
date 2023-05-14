package com.example.redditfortablet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.rememberNavController
import com.dudqo.eventplanner.ui.theme.RedditForTabletTheme
import com.example.redditfortablet.graphs.RootNavGraph

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RedditForTabletTheme{


                RootNavGraph(navController = rememberNavController())



            }




        }


    }
}

