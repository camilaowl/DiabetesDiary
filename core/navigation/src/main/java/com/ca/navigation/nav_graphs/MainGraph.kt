package com.ca.navigation.nav_graphs

import kotlinx.serialization.Serializable

@Serializable
sealed class MainGraph : TopLevelDestination {
    @Serializable
    data object OnBoarding : MainGraph()
    @Serializable
    data object Home : MainGraph()
    @Serializable
    data object Settings : MainGraph()
}
