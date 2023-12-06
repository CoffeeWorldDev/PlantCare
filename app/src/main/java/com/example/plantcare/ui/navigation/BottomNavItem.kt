package com.example.plantcare.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(var title:String, var icon:ImageVector, var screen_route:String){

    var route : String = "bottom_nav_route"
    object HomeScreen : BottomNavItem("Home", Icons.Filled.Home ,"home_screen")
    object PlantsGallery: BottomNavItem("Plants", Icons.Filled.Favorite ,"plants_screen")
    object Settings: BottomNavItem("Settings", Icons.Filled.Settings,"settings_screen")
}