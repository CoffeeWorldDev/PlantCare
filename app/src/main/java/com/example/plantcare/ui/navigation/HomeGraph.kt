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
import com.example.plantcare.ui.home.Home

fun NavGraphBuilder.addHomeGraph(
    onPlantSelected: (Long, NavBackStackEntry) -> Unit,
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    composable(HomeSections.FEED.route) { from ->
        Home(onPlantClick = { id -> onPlantSelected(id, from) },
                                        onNavigateToRoute,
                                        modifier = Modifier.fillMaxSize()
                                            .background(MaterialTheme.colorScheme.background)
                                            .wrapContentSize(align = Alignment.TopCenter))
    }
    composable(HomeSections.GALLERY.route) { from ->
        PlantsGallery(onPlantClick = { id -> onPlantSelected(id, from) },
                                onNavigateToRoute,
                                modifier = Modifier.fillMaxSize()
                                    .background(MaterialTheme.colorScheme.background)
                                    .wrapContentSize(align = Alignment.TopCenter))
    }
    composable(HomeSections.SETTINGS.route) {
        Settings(onNavigateToRoute, modifier)
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

