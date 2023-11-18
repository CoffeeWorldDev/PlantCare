package com.example.plantcare.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.plantcare.ui.BottomNavBar.BottomNavItem
import com.example.plantcare.ui.home.HomeBaseScreen
import com.example.plantcare.ui.plantsGallery.PlantsGalleryScreenBase

@Composable
fun PlantCareNavHost(
    navController: NavHostController = rememberNavController()
){

    NavHost(navController = navController, startDestination = BottomNavItem.HomeScreen.screen_route){

        composable(BottomNavItem.HomeScreen.screen_route){
            HomeBaseScreen(modifier = Modifier.fillMaxSize()
                                      .background(MaterialTheme.colorScheme.background)
                                      .wrapContentSize(align = Alignment.TopCenter)
            )
        }
        composable(BottomNavItem.PlantsGallery.screen_route){
            PlantsGalleryScreenBase(modifier = Modifier.fillMaxSize()
                                              .background(MaterialTheme.colorScheme.background)
                                              .wrapContentSize(align = Alignment.TopCenter))
        }
        composable(BottomNavItem.Settings.screen_route){
        //    SettingsScreen()
        }

       composable(route = screens.PlantDetails.name){
        //   PlantDetailsScreenBase(uiState)
       }

        composable(route = screens.PlantCreateEdit.name){
        //    PlantCreationEditBase(uiState)
        }
    }
}

enum class screens() {
    PlantDetails,
    PlantCreateEdit
}