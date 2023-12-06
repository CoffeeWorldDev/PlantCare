package com.example.plantcare.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.plantcare.ui.navigation.HomeSections
import com.example.plantcare.ui.navigation.addHomeGraph
import com.example.plantcare.ui.navigation.MainDestinations
import com.example.plantcare.ui.navigation.rememberPlantCareNavController
import com.example.plantcare.ui.plantCreationEdit.PlantCreationEditBase

@Composable
fun PlantCareNavGraph(){
    val plantCareNavController = rememberPlantCareNavController()
    NavHost(
        navController = plantCareNavController.navController,
        startDestination = MainDestinations.HOME_ROUTE
    ){
        plantCareNavGraph(
            onPlantSelected = plantCareNavController::navigateToPlantDetail,
            upPress = plantCareNavController::upPress,
            onNavigateToRoute = plantCareNavController::navigateToBottomBarRoute
        )
      //  bottomNavGraph(plantCareNavController)
      //  plantNavGraph(plantCareNavController)
    }
}

private fun NavGraphBuilder.plantCareNavGraph(
    onPlantSelected: (Long, NavBackStackEntry) -> Unit,
    upPress: () -> Unit,
    onNavigateToRoute: (String) -> Unit
){
    navigation(
        route = MainDestinations.HOME_ROUTE,
        startDestination = HomeSections.FEED.route
    ){
        addHomeGraph(onPlantSelected, onNavigateToRoute)
    }

}





fun NavGraphBuilder.plantNavGraph(navController: NavController) {
    navigation(startDestination = PlantNav.PLANT_DETAILS_SCREEN,
                route = PlantNav.PLANTNAV_ROUTE) {
        composable(PlantNav.PLANT_DETAILS_SCREEN){
           // PlantDetailsScreenBase(modifier = Modifier.fillMaxSize()
           //     .background(MaterialTheme.colorScheme.background)
           //     .wrapContentSize(align = Alignment.TopCenter)
           // )
        }
        composable(PlantNav.PLANT_EDIT_CREATION_SCREEN){
            PlantCreationEditBase(modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .wrapContentSize(align = Alignment.TopCenter))
        }
    }
}

object PlantNav {
    const val PLANTNAV_ROUTE = "plantNav"
    const val PLANT_DETAILS_SCREEN = "PlantDetails"
    const val PLANT_EDIT_CREATION_SCREEN = "PlantCreateEdit"
}

enum class screens() {
    PlantDetails,
    PlantCreateEdit
}