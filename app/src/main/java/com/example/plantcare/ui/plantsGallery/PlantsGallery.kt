package com.example.plantcare.ui.plantsGallery

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
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
import com.example.plantcare.ui.navigation.HomeSections
import com.example.plantcare.ui.navigation.PlantCareBottomBar
import com.example.plantcare.ui.utils.AddFloatingBtn

//TODO delete
val column : Int = 2

@Composable
fun PlantsGallery(onPlantClick: (String, Long) -> Unit,
                  onNavigateToRoute: (String) -> Unit,
                  onCreateNew: (Long) -> Unit,
                  modifier: Modifier = Modifier,
                  viewModel: PlantsGalleryViewModel = hiltViewModel()) {
    val galleryUiState by viewModel.uiState.collectAsStateWithLifecycle()
    //Log.e("PLANTS GALLERY", galleryUiState.toString())

    Scaffold(
        bottomBar = {
            PlantCareBottomBar(
                tabs = HomeSections.entries.toTypedArray(),
                currentRoute = HomeSections.GALLERY.route,
                navigateToRoute = onNavigateToRoute
            )
        },
        floatingActionButton = {
            AddFloatingBtn(
                onClick = { onCreateNew(-1) },
                modifier = Modifier.height(50.dp)
                    .width(50.dp)
            )
        },
        floatingActionButtonPosition = FabPosition.End
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
            PlantsGalleryHeader(
                galleryUiState.plantsMap,
                onValueChange = { viewModel.sortGallery(3) },
                modifier = Modifier
            )
            if (galleryUiState.plantsMap?.isEmpty() == false) {
                when (galleryUiState.currentLayout) {
                    1 -> PlantsGalleryBodyVerL1(galleryUiState.plantsMap,
                        onPlantClick = onPlantClick,
                        modifier = Modifier)
                    //  2 -> PlantsGalleryBodyVerL2(galleryUiState.plantsMap,
                    //      onPlantClick = {onPlantClick(it)}, 1)
                    3 -> PlantsGalleryBodyVerL3(galleryUiState.plantsMap, 2)
                }
            }
        }
    }
}
