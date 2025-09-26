package com.cornellappdev.hustle.ui.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cornellappdev.hustle.ui.navigation.MessagesDestination
import com.cornellappdev.hustle.ui.navigation.MessagesTab

fun NavGraphBuilder.messagesNavGraph(navController: NavHostController) {
    navigation<MessagesTab>(startDestination = MessagesDestination.Messages) {
        composable<MessagesDestination.Messages> {}
    }
}