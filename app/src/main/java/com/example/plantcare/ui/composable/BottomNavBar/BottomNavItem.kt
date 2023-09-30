package com.example.plantcare.ui.composable.BottomNavBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(var title:String, var icon:ImageVector, var screen_route:String){

    object HomeScreen : BottomNavItem("Home", Icons.Filled.Home ,"home_screen")
    object PlantsGallery: BottomNavItem("Plants", Icons.Filled.Favorite ,"plants_screen")
    object Settings: BottomNavItem("Settings", Icons.Filled.Settings,"settings_screen")
}