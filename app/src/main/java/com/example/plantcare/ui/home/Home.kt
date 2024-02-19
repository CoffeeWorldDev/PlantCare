package com.example.plantcare.ui.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.plantcare.ui.navigation.HomeSections
import com.example.plantcare.ui.navigation.PlantCareBottomBar

@Composable
fun Home(
    onPlantClick: (Long) -> Unit,
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeUiState by viewModel.uiState.collectAsStateWithLifecycle()
    Log.e("HOME BASE", homeUiState.toString())


    Scaffold(
        bottomBar = {
            PlantCareBottomBar(
                tabs = HomeSections.entries.toTypedArray(),
                currentRoute = HomeSections.FEED.route,
                navigateToRoute = onNavigateToRoute
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(
                PaddingValues(
                    0.dp,
                    0.dp,
                    0.dp,
                    innerPadding.calculateBottomPadding()
                )
            )
        ) {
            HomeTop(
                onValueChange = { viewModel.changeQuery(it) },
                modifier = Modifier
            )
            HomeMainBlock(homeUiState.plantsMap,
                onTaskClicked = { viewModel.updateTask(it)})
        }
    }
}

//    Column() {
//        HomeTop(
//            onValueChange = { viewModel.changeQuery(it) },
//            modifier = Modifier
//        )
//    }
//
//        HomeMainBlock(homeUiState.plantsMap,
//                      onTaskClicked = { viewModel.updateTask(it)})
//
//    Box( modifier = Modifier
//        .fillMaxSize()
//        .padding(0.dp, 40.dp),
//        contentAlignment = Alignment.BottomEnd
//    ){
//        //TODO make plant button hover
//        FloatingActionButton(onClick = { navController.navigate(PlantNav.PLANT_EDIT_CREATION_SCREEN) },
//            containerColor = Color.White,
//            shape = RoundedCornerShape(50.dp),
//            modifier = Modifier
//                .padding(20.dp, 0.dp)
//                .height(50.dp)
//                .width(50.dp)
//                .offset(0.dp, 20.dp)
//        ) {
//            Icon(Icons.Filled.Add, "Floating action button.")
//        }
//    }
//    }
// fun HomeBaseScreen(
//    onDateChanged: (Date) -> Unit,

