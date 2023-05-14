package com.example.redditfortablet.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.redditfortablet.BottomNavItem
import com.example.redditfortablet.graphs.MainNavGraph


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavBarScreen(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(
                        name = "Home",
                        route = "home",
                        icon_default = Icons.Outlined.Home,
                        icon_selected = Icons.Filled.Home
                    ),
                    BottomNavItem(
                        name = "Subscriptions",
                        route = "subscriptions",
                        icon_default = Icons.Outlined.List,
                        icon_selected = Icons.Filled.List
                    ),
                    BottomNavItem(
                        name = "Account",
                        route = "account",
                        icon_default = Icons.Outlined.Person,
                        icon_selected = Icons.Filled.Person
                    ),
                    BottomNavItem(
                        name = "Settings",
                        route = "settings",
                        icon_default = Icons.Outlined.Settings,
                        icon_selected = Icons.Filled.Settings
                    ),
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route) {
                        launchSingleTop = true
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                    }
                }
            )

        }
    ) {
        Box(
            modifier = Modifier.padding(it)
        ) {
            MainNavGraph(navController = navController)
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStateEntry = navController.currentBackStackEntryAsState()
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        items.forEach {item ->
            val selected = item.route == backStateEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item) },
                icon = {
                    if (selected) {
                        Icon(
                            imageVector = item.icon_selected,
                            contentDescription = item.name
                        )
                    } else {
                        Icon(
                            imageVector = item.icon_default,
                            contentDescription = item.name
                        )
                    }

                }
            )
        }
    }
}