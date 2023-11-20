package com.example.plantcare.ui.plantsGallery

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.plantcare.ui.util.AddFloatingBtn

//TODO delete
val column : Int = 2

@Composable
fun PlantsGalleryScreenBase(viewModel: PlantsGalleryViewModel = hiltViewModel(),
                            modifier: Modifier = Modifier){
    val galleryUiState by viewModel.uiState.collectAsStateWithLifecycle()
    Log.e("PLANTS GALLERY", galleryUiState.toString())


    Column {

        PlantsGalleryTop(galleryUiState.plantsMap,
                         onValueChange = { viewModel.SortGallery(it) },
                         modifier = Modifier)

        if (galleryUiState.plantsMap?.isEmpty() == false){
            when (galleryUiState.currentLayout){
                1 -> PlantsGalleryMainBlockL1(galleryUiState.plantsMap)
                2 -> PlantsGalleryMainBlockL2(galleryUiState.plantsMap, 1)
                3 -> PlantsGalleryMainBlockL3(galleryUiState.plantsMap, 2)
            }
        } else {
            //TODO center add plant button
            AddFloatingBtn(
                modifier = Modifier
                    .padding(20.dp, 0.dp)
                    .height(50.dp)
                    .width(50.dp)
            )
        }

    }
}