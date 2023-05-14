package com.example.redditfortablet.graphs

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.redditfortablet.presentation.home.HomeScreen

@ExperimentalMaterial3Api
@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(/*navController = navController*/)
        }
        composable("events") {
            /*EventsScreen(navController = navController)*/
        }
        composable("friends") {
            /*FriendsScreen()*/
        }
        composable("settings") {
            /*SettingsScreen(navController = navController)*/
        }
        subsNavGraph(navController = navController)
        settingsNavGraph(navController = navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.subsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.SUBSCRIPTION,
        startDestination = EventScreen.CreateScreen.route + "?eventId={eventId}"
    ) {
        composable(
            route = EventScreen.CreateScreen.route + "?eventId={eventId}",
            arguments = listOf(
                navArgument(
                    name = "eventId"
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            /*EventCreateScreen(navController)*/
        }
        composable(
            route = EventScreen.ViewScreen.route + "?eventId={eventId}",
            arguments = listOf(
                navArgument(
                    name = "eventId"
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            /*EventViewScreen(navController)*/
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.settingsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.SETTINGS,
        startDestination = SettingsScreen.ProfileScreen.route
    ) {
        composable(
            route = SettingsScreen.ProfileScreen.route
        ) {
            /*ProfileScreen(navController)*/
        }
        composable(
            route = SettingsScreen.EditProfileScreen.route
        ) {
            /*EditProfileScreen(navController)*/
        }
    }
}

sealed class EventScreen(val route: String) {
    object CreateScreen: EventScreen(route = "create_screen")
    object ViewScreen: EventScreen(route = "view_screen")
}

sealed class SettingsScreen(val route: String) {
    object ProfileScreen: SettingsScreen(route = "profile_screen")
    object EditProfileScreen: SettingsScreen(route = "edit_profile_screen")

}