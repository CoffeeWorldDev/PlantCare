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
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.plantcare.ui.navigation.HomeSections
import com.example.plantcare.ui.navigation.addHomeGraph
import com.example.plantcare.ui.navigation.MainDestinations
import com.example.plantcare.ui.navigation.addPlantGraph
import com.example.plantcare.ui.navigation.rememberPlantCareNavController
import com.example.plantcare.ui.plantCreationEdit.PlantCreationEdit

@Composable
fun PlantCareNavGraph(){
    val plantCareNavController = rememberPlantCareNavController()
    NavHost(
        navController = plantCareNavController.navController,
        startDestination = MainDestinations.HOME_ROUTE
    ){
        plantCareNavGraph(
            controller = plantCareNavController.navController,
            onPlantSelected = plantCareNavController::navigateToEditCreate,
            upPress = plantCareNavController::upPress,
            onNavigateToRoute = plantCareNavController::navigateToBottomBarRoute,
            navigateWithinPlantDetails = plantCareNavController::navigateWithinPlantDetails,
            navigateBackToGallery = plantCareNavController::navigateBackToGallery
        )
    }
}

private fun NavGraphBuilder.plantCareNavGraph(
    controller : NavHostController,
    onPlantSelected: (Long, NavBackStackEntry) -> Unit,
    upPress: () -> Unit,
    onNavigateToRoute: (String) -> Unit,
    navigateWithinPlantDetails: (String, Long, NavBackStackEntry) -> Unit,
    navigateBackToGallery : (NavBackStackEntry) -> Unit
){
    addHomeGraph(onPlantSelected, onNavigateToRoute, navigateWithinPlantDetails)
    addPlantGraph(controller, navigateWithinPlantDetails, upPress, navigateBackToGallery)
}

