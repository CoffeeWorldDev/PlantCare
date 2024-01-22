package com.example.plantcare.ui.plantsGallery

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.plantcare.ui.PlantNav
import com.example.plantcare.ui.navigation.HomeSections
import com.example.plantcare.ui.navigation.PlantCareBottomBar

//TODO delete
val column : Int = 2

@Composable
fun PlantsGallery(onPlantClick: (Long) -> Unit,
                  onNavigateToRoute: (String) -> Unit,
                  modifier: Modifier = Modifier,
                  viewModel: PlantsGalleryViewModel = hiltViewModel()) {
    val galleryUiState by viewModel.uiState.collectAsStateWithLifecycle()
    Log.e("PLANTS GALLERY", galleryUiState.toString())

    Scaffold(
        bottomBar = {
            PlantCareBottomBar(
                tabs = HomeSections.entries.toTypedArray(),
                currentRoute = HomeSections.GALLERY.route,
                navigateToRoute = onNavigateToRoute
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(
                    PaddingValues(
                        0.dp,
                        0.dp,
                        0.dp,
                        innerPadding.calculateBottomPadding()
                    )
                )
        ) {
            PlantsGalleryHeader(
                galleryUiState.plantsMap,
                onValueChange = { viewModel.sortGallery(3) },
                modifier = Modifier
            )
            Box {
                if (galleryUiState.plantsMap?.isEmpty() == false) {
                    when (galleryUiState.currentLayout) {
                        1 -> PlantsGalleryBodyVerL1(galleryUiState.plantsMap,
                            onPlantClick = {onPlantClick(it)},
                            modifier = Modifier)
                        2 -> PlantsGalleryBodyVerL2(galleryUiState.plantsMap,
                            onPlantClick = {onPlantClick(it)}, 1)
                        3 -> PlantsGalleryBodyVerL3(galleryUiState.plantsMap, 2)
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp, 40.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    //TODO make plant button hover
                    FloatingActionButton(
                        onClick = { onPlantClick(-1) },
                        containerColor = Color.White,
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .padding(20.dp, 0.dp)
                            .height(50.dp)
                            .width(50.dp)
                            .offset(0.dp, 20.dp)
                    ) {
                        Icon(Icons.Filled.Add, "Floating action button.")
                    }
                }
            }
        }
    }
}