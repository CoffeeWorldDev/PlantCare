package com.example.plantcare.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.plantcare.data.model.Plants
import com.example.plantcare.ui.composable.BottomNavBar.BottomNavItem
import com.example.plantcare.ui.composable.plantDetails.PlantDetailsScreenBase
import com.example.plantcare.ui.composable.home.HomeBaseScreen
import com.example.plantcare.ui.composable.plantsGallery.PlantsGalleryScreenBase
import com.example.plantcare.ui.composable.SettingsScreen
import com.example.plantcare.ui.composable.plantCreationEdit.PlantCreationEditBase

@Composable
fun PlantCareNavHost(
    navController: NavHostController,
    plants: List<Plants>
){

    NavHost(navController = navController, startDestination = "plant_create_edit_screen"){

//        composable(BottomNavItem.HomeScreen.screen_route){
//            HomeBaseScreen(plants)
//        }
//              composable(BottomNavItem.PlantsGallery.screen_route){
//            PlantsGalleryScreenBase(plants,  onNavigateToSecondScreen = {id
//                navController.navigate("plant_detail_screen/$it")
//            })
//        }
//
//              composable(BottomNavItem.Settings.screen_route){
//            SettingsScreen()
//        }
//
//       composable(route = "plant_detail_screen",
//           //TODO delete and make a proper data sharing with viewModel
//           arguments = listOf(
//               navArgument("inputName"){
//                   type = NavType.StringType
//               })){
//           PlantDetailsScreenBase(plants)
//       }
//
//        composable(route = "plant_create_edit_screen"){
//            PlantCreationEditBase(plants)
//        }
    }
}
