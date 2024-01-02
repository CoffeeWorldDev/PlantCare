package com.example.plantcare.ui.plantsGallery

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.ui.components.PlantCareImage

@Composable
fun PlantsGalleryBodyVerL3(
    plants: Map<Plants?, List<Tasks>>?,
    columns: Int,
   //onNavigateToSecondScreen: (String) -> Unit
) {
    val plantsList = plants?.toList()
    LazyVerticalGrid(columns = GridCells.Fixed(columns),
        modifier = Modifier.fillMaxSize()){
        items(plantsList!!.size) {plant ->
            Column(modifier = Modifier) {
                PlantCareImage(imageUrl = plantsList[plant].first!!.photo!!,
                    contentDescription = "plant image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )
                if(columns < 3)
                    Text(text = plantsList[plant].first!!.name,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center)
                }
            }
        }
    }
