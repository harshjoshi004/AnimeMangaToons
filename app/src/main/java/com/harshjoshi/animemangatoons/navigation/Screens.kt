package com.harshjoshi.animemangatoons.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import com.harshjoshi.animemangatoons.navigation.Screens.HomeScreen
import com.harshjoshi.animemangatoons.navigation.Screens.SavedScreen

sealed class Screens(val title: String, val route: String, val icon: ImageVector? = null) {
    object DetailScreen: Screens("Details", "detail_screen", Icons.Default.Info)
    object DetailScreen2: Screens("Details", "detail_screen2", Icons.Default.Info)
    object HomeScreen: Screens("Home", "home_screen", Icons.Default.Home)
    object SavedScreen: Screens("Saved Webtoons", "saved_screen", Icons.Default.CloudDownload)
}

val listOfScreens = listOf(HomeScreen, SavedScreen)