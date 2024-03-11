package com.example.plantcare.ui.plantsGallery

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.plantcare.R
import com.example.plantcare.data.model.Plants
import com.example.plantcare.ui.components.PlantCareImage
import com.example.plantcare.ui.navigation.PlantSections
import com.example.plantcare.ui.utils.getElapsedTime

@Composable
fun PlantsGalleryBodyVerL2(
    plants: List<Plants>,
    columns: Int,
    onPlantClick: (String, Long) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns)
    ){
        items(plants.size) {
            Row(
               modifier = Modifier
                   .padding(start = 15.dp, bottom =  20.dp)
                   .clickable {
                       onPlantClick(
                           PlantSections.PLANT.route,
                           plants[it].plantsId
                       )
                   }
           ) {
               PlantCareImage(
                   imageUrl = plants[it].photo,
                   contentDescription = stringResource(id = R.string.photo_description),
                   modifier = Modifier
                       .border(
                           width = 1.dp,
                           color = Color.Black)
                       .weight(1.1f)
                       .height(95.dp)
               )
               Column(
                   modifier = Modifier
                           .weight(3f)
                           .padding(start = 10.dp),
                   horizontalAlignment = Alignment.End
               ) {
                   Text(
                       text = plants[it].name,
                       modifier = Modifier.fillMaxWidth(),
                       textAlign = TextAlign.Center
                   )
                   Divider(
                       thickness = 1.dp,
                       modifier = Modifier.padding(bottom = 5.dp)
                   )
                   Text(
                       text = "${getElapsedTime(plants[it].age)} days old" ,
                       modifier = Modifier.fillMaxWidth(),
                       textAlign = TextAlign.Center
                   )
                   Text(
                       text = plants[it].type,
                       modifier = Modifier.fillMaxWidth(),
                       textAlign = TextAlign.Center
                   )
               }
           }
            if(it == plants.size-1){
                Spacer(
                    modifier = Modifier.padding(bottom = 35.dp)
                )
            }
        }
    }
}
