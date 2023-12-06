package com.example.plantcare.ui.navigation


import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

///@Composable
///fun BottomNav(navController: NavController) {
///    val items = listOf(
///        BottomNavItem.HomeScreen,
///        BottomNavItem.PlantsGallery,
///        BottomNavItem.Settings
///    )
///    navController.navigatorProvider
///    NavigationBar(
///        containerColor = MaterialTheme.colorScheme.primaryContainer,
///        contentColor = Color.White,
///        modifier = Modifier
///    ) {
///        val navBackStackEntry by navController.currentBackStackEntryAsState()
/////        val currentRoute = navBackStackEntry?.destination?.route
///        val currentDestination = navBackStackEntry?.destination
///        items.forEach{  item ->
///            NavigationBarItem(
///                icon = { Icon(item.icon, item.title) },
///                label = { Text(item.title) },
/////                selected = currentRoute == item.screen_route,
///                selected = currentDestination?.hierarchy?.any { it.route == item.screen_route } == true,
///                onClick = {
///                  navController.navigate(item.screen_route) {
///                      popUpTo(navController.graph.findStartDestination().id) {
///                          saveState = true
///                      }
///                      launchSingleTop = true
///                      restoreState = true
///                  }
///              }
///          )
///        }
///    }
///}
///
///