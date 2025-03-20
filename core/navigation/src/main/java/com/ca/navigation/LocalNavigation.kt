package com.ca.navigation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController

val LocalNavigation = staticCompositionLocalOf<NavController> {
    error("No LocalNavigation Provided")
}