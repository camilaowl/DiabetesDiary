package com.ca.authentication.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ca.authentication.presentation.AuthScreen
import com.ca.navigation.nav_graphs.AuthGraph


fun NavGraphBuilder.authNavGraph(
    onComplete: () -> Unit
) {
    navigation<AuthGraph>(
        startDestination = AuthGraph.Login,
    ) {
        composable<AuthGraph.Login> {
            AuthScreen(
                onComplete = onComplete
            )
        }
    }
}