package com.cornellappdev.hustle.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cornellappdev.hustle.R

data class BottomNavigationItem<T : AppDestination>(
    val route: T,
    val title: String,
    @DrawableRes val icon: Int,
    @DrawableRes val selectedIcon: Int = icon,
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        route = HomeTab,
        title = "Home",
        icon = R.drawable.ic_home,
        selectedIcon = R.drawable.ic_home,
    ),
    BottomNavigationItem(
        route = MessagesTab,
        title = "Messages",
        icon = R.drawable.ic_messages,
        selectedIcon = R.drawable.ic_messages,
    ),
    BottomNavigationItem(
        route = ProfileTab,
        title = "Profile",
        icon = R.drawable.ic_profile,
        selectedIcon = R.drawable.ic_profile,
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
                        painter = painterResource(id = if (selected) item.selectedIcon else item.icon),
                        contentDescription = item.title,
                        tint = Color.Unspecified
                    )
                }, selected = selected, onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }, label = {
                    Text(item.title)
                })
            }
        }
    }
}