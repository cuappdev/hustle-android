package com.cornellappdev.hustle.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

data class BottomNavigationItem<T : AppDestination>(
    val route: T,
    val title: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector = icon,
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        route = HomeTab,
        title = "Home",
        icon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
    ),
    BottomNavigationItem(
        route = FavoritesTab,
        title = "Favorites",
        icon = Icons.Outlined.FavoriteBorder,
        selectedIcon = Icons.Filled.Favorite,
    ),
    BottomNavigationItem(
        route = ProfileTab,
        title = "Profile",
        icon = Icons.Outlined.Person,
        selectedIcon = Icons.Filled.Person,
    ),
)

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val shouldShow = bottomNavigationItems.any { item ->
        currentDestination?.hierarchy?.any {
            it.hasRoute(item.route::class)
        } == true
    }

    if (shouldShow) {
        NavigationBar {
            bottomNavigationItems.forEach { item ->
                val selected = currentDestination?.hierarchy?.any {
                    it.hasRoute(item.route::class)
                } == true

                NavigationBarItem(icon = {
                    Icon(
                        if (selected) item.selectedIcon else item.icon,
                        contentDescription = item.title
                    )
                }, selected = selected, onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
            }
        }
    }
}