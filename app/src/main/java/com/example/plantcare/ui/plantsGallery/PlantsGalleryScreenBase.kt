package com.example.plantcare.ui.plantsGallery

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.plantcare.ui.home.HomeViewModel

//TODO delete
val column : Int = 2

@Composable
fun PlantsGalleryScreenBase(viewModel: HomeViewModel = hiltViewModel(),
                            modifier: Modifier = Modifier){
    val plantsState by viewModel.uiState.collectAsStateWithLifecycle()
    Log.e("PLANTS GALLERY", plantsState.toString())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .wrapContentSize(align = Alignment.TopCenter)
    ) {
        PlantsGalleryTop(plantsState.plantsMap,
            modifier = Modifier)
    //    PlantsGalleryMainBlockL2(plants)
        //PlantsGalleryMainBlockL3(plants, column, onNavigateToSecondScreen)
        //PlantsGalleryMainBlockL1(plants, onNavigateToSecondScreen)
    }
}