package com.ca.navigation.nav_graphs

import kotlinx.serialization.Serializable

@Serializable
data object AuthGraph : TopLevelDestination {
    @Serializable
    data object Login
}