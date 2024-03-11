package com.example.plantcare.ui.plantsGallery

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.plantcare.data.model.Plants
import com.example.plantcare.ui.components.PlantCareImage
import com.example.plantcare.ui.navigation.PlantSections

@Composable
fun PlantsGalleryBodyVerL3(
    plants: List<Plants>,
    columns: Int,
    onPlantClick: (String, Long) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = Modifier.padding()
    ) {
        items(plants.size) {
            Row(
                modifier = Modifier
                    .padding(start = 15.dp, bottom = 20.dp, end = 15.dp)
                    .clickable {
                        onPlantClick(
                            PlantSections.PLANT.route,
                            plants[it].plantsId
                        )
                    }
            ) {
                Column {
                    PlantCareImage(
                        imageUrl = plants[it].photo,
                        contentDescription = "plant image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    )
                    if (columns < 3)
                        Text(
                            text = plants[it].name,
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                }
            }
            if (it == plants.size - 1) {
                Spacer(
                    modifier = Modifier.padding(bottom = 35.dp)
                )
            }
        }
    }
}