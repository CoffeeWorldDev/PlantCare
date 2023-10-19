package com.example.plantcare.ui.plantsGallery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.plantcare.ui.home.HomeViewModel

//TODO delete
val column : Int = 2

@Composable
fun PlantsGalleryScreenBase(plants: HomeViewModel.PlantsState){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .wrapContentSize(align = Alignment.TopCenter)
    ) {
    //    PlantsGalleryTop(modifier = Modifier, plants)
    //    PlantsGalleryMainBlockL2(plants)
        //PlantsGalleryMainBlockL3(plants, column, onNavigateToSecondScreen)
        //PlantsGalleryMainBlockL1(plants, onNavigateToSecondScreen)
    }
}