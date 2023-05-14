package com.example.redditfortablet.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.redditfortablet.presentation.BottomNavBarScreen

@Composable
fun RootNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.MAIN
    ) {
        composable(route = Graph.MAIN) {
            BottomNavBarScreen()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val MAIN = "main_graph"
    const val SUBSCRIPTION = "subscription_graph"
    const val ACCOUNT = "account_graph"
    const val SETTINGS = "setting_graph"
}