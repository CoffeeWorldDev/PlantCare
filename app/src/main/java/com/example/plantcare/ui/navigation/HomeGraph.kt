package com.example.plantcare.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.plantcare.R
import com.example.plantcare.ui.Settings
import com.example.plantcare.ui.plantsGallery.PlantsGallery
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.plantcare.ui.home.Home
import com.example.plantcare.ui.plantCreationEdit.PlantCreationEdit
import com.example.plantcare.ui.plantCreationEdit.PlantDetailsViewModel
import com.example.plantcare.ui.plantDetails.NotesScreen
import com.example.plantcare.ui.plantDetails.PlantDetails
import com.example.plantcare.ui.plantDetails.TaskListScreen

fun NavGraphBuilder.addHomeGraph(
    onPlantSelected: (Long, NavBackStackEntry) -> Unit,
    onNavigateToRoute: (String) -> Unit,
    navigateWithinPlantDetails: (String, Long, NavBackStackEntry) -> Unit,
    modifier: Modifier = Modifier
) {
    navigation(
        route = MainDestinations.HOME_ROUTE,
        startDestination = HomeSections.FEED.route
    ){
        composable(HomeSections.FEED.route) { from ->
            Home(onPlantClick = { id -> onPlantSelected(id, from) },
                onNavigateToRoute,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .wrapContentSize(align = Alignment.TopCenter))
        }
        composable(HomeSections.GALLERY.route) { from ->
            PlantsGallery(
                onPlantClick = { route, id -> navigateWithinPlantDetails(route, id, from) },
                onNavigateToRoute,
                onCreateNew = { id -> onPlantSelected(id, from) },
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .wrapContentSize(align = Alignment.TopCenter)
            )
        }
        composable(HomeSections.SETTINGS.route) {
            Settings(onNavigateToRoute, modifier)
        }
    }

}
fun NavGraphBuilder.addPlantGraph(
    controller : NavHostController,
    navigateWithinPlantDetails: (String, Long, NavBackStackEntry) -> Unit,
    upPress: () -> Unit,
    navigateBackToGallery : (NavBackStackEntry) -> Unit,
    modifier: Modifier = Modifier
) {
    navigation(
        route = MainDestinations.PLANT_DETAIL_ROUTE,
        startDestination = PlantSections.PLANT.route
    ) {
        composable(
            route = "${PlantSections.PLANT.route}/{${MainDestinations.PLANT_ID_KEY}}",
            arguments = listOf(navArgument(MainDestinations.PLANT_ID_KEY) { type = NavType.LongType })
        ) { from ->
            val arguments = requireNotNull(from.arguments)
            val plantId = arguments.getLong(MainDestinations.PLANT_ID_KEY)
            val plantDetailsBackStackEntry = remember(from) { controller.getBackStackEntry(MainDestinations.PLANT_DETAIL_ROUTE) }
            val plantDetailsViewModel: PlantDetailsViewModel = hiltViewModel(plantDetailsBackStackEntry)
            PlantDetails(
                plantId = plantId,
                onNavigateToDetail = { route, id -> navigateWithinPlantDetails(route, id, from) },
                upPress = upPress,
                viewModel = plantDetailsViewModel
            )
        }
        composable(
            route = "${PlantSections.TASKS.route}/{${MainDestinations.PLANT_ID_KEY}}",
            arguments = listOf(navArgument(MainDestinations.PLANT_ID_KEY) { type = NavType.LongType })
        ) { from ->
            val arguments = requireNotNull(from.arguments)
            val plantId = arguments.getLong(MainDestinations.PLANT_ID_KEY)
            val plantDetailsBackStackEntry = remember(from) { controller.getBackStackEntry(MainDestinations.PLANT_DETAIL_ROUTE) }
            val plantDetailsViewModel: PlantDetailsViewModel = hiltViewModel(plantDetailsBackStackEntry)

            TaskListScreen(
                plantId = plantId,
                upPress = upPress,
                viewModel = plantDetailsViewModel
            )
        }
        composable(
            route = "${PlantSections.NOTES.route}/{${MainDestinations.PLANT_ID_KEY}}",
            arguments = listOf(navArgument(MainDestinations.PLANT_ID_KEY) { type = NavType.LongType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val plantId = arguments.getLong(MainDestinations.PLANT_ID_KEY)
            NotesScreen(
                plantId = plantId,
                upPress = upPress
            )
        }
        composable(
            route = "${PlantSections.EDIT_CREATE.route}/{${MainDestinations.PLANT_ID_KEY}}",
            arguments = listOf(navArgument(MainDestinations.PLANT_ID_KEY) { type = NavType.LongType })
        ) { from ->
            val arguments = requireNotNull(from.arguments)
            val plantId = arguments.getLong(MainDestinations.PLANT_ID_KEY)
            val plantDetailsBackStackEntry = remember(from) { controller.getBackStackEntry(MainDestinations.PLANT_DETAIL_ROUTE) }
            val plantDetailsViewModel: PlantDetailsViewModel = hiltViewModel(plantDetailsBackStackEntry)

            PlantCreationEdit(
                plantId = plantId,
                onNavigateToDetail = { route, id -> navigateWithinPlantDetails(route, id, from) },
                navigateBackToGallery = { navigateBackToGallery(from) },
                upPress = upPress,
                viewModel = plantDetailsViewModel
            )
        }
    }
}

enum class HomeSections(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
    FEED(R.string.bottom_nav_feed, Icons.Filled.Home, "home/feed"),
    GALLERY(R.string.bottom_nav_gallery, Icons.Filled.Favorite, "home/gallery"),
    SETTINGS(R.string.bottom_nav_settings, Icons.Filled.Settings, "home/settings")
}
enum class PlantSections(
    @StringRes val title: Int,
    val route: String
) {
    PLANT(R.string.plant_nav_main,  "plant/main"),
    TASKS(R.string.plant_nav_tasks,  "plant/tasks"),
    NOTES(R.string.plant_nav_notes,  "plant/notes"),
    EDIT_CREATE(R.string.plant_nav_edit_create,  "plant/edit_create")
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

@Composable
fun PlantCareBottomBar(
    tabs: Array<HomeSections>,
    currentRoute: String,
    navigateToRoute: (String) -> Unit
) {
    val routes = remember { tabs.map { it.route } }
    val currentSection = tabs.first { it.route == currentRoute }
    val springSpec = SpringSpec<Float>(
        // Determined experimentally
        stiffness = 800f,
        dampingRatio = 0.8f
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = Color.White,
        modifier = Modifier
    ) {

        tabs.forEach{  tab ->
            val selected = tab == currentSection
            val text = stringResource(tab.title)

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = text
                    )
                },
                label = {
                    Text(
                        text = text
                    )
                },
                selected = currentRoute == tab.route,
                // selected = selected,
                onClick = { navigateToRoute(tab.route) }
            )
        }
    }
}

