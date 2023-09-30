package com.example.plantcare.ui.composable.plantsGallery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.plantcare.data.model.Plants

//TODO delete
val column : Int = 2

@Composable
fun PlantsGalleryScreenBase(plants: List<Plants>, onNavigateToSecondScreen : (String)->Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .wrapContentSize(align = Alignment.TopCenter)
    ) {
        PlantsGalleryTop(modifier = Modifier, plants)
        PlantsGalleryMainBlockL2(plants, column,onNavigateToSecondScreen)
        //PlantsGalleryMainBlockL3(plants, column, onNavigateToSecondScreen)
        //PlantsGalleryMainBlockL1(plants, onNavigateToSecondScreen)
    }
}