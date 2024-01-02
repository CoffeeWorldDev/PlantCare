package com.example.plantcare.ui.plantsGallery

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.ui.components.PlantCareImage

@Composable
fun PlantsGalleryBodyVerL2(
    plants: Map<Plants?, List<Tasks>>?,
    onPlantClick: (Long) -> Unit,
    columns: Int,
    //onNavigateToSecondScreen: (String) -> Unit
) {
    val plantsList = plants?.toList()
    //var plantsList : Set<Plants> = plants
    LazyVerticalGrid(columns = GridCells.Fixed(columns),
                     modifier = Modifier.fillMaxSize()){
        items(plantsList!!.size) {plant ->
           Row(modifier = Modifier.padding(15.dp, 0.dp,0.dp, 20.dp)
               .clickable {onPlantClick(plantsList[plant].first!!.plantsId)
               Log.d("ERROR", plantsList[plant].first!!.plantsId.toString())}) {
               Card {

               }
               PlantCareImage(imageUrl = plantsList[plant].first!!.photo!!,
                   contentDescription = "plant image",
                   modifier = Modifier
                       .border(1.dp, color = Color.Black)
                       .weight(1.1f)
                       .height(95.dp)
               )
               Column(modifier = Modifier
                   .weight(3f)
                   .padding(10.dp, 0.dp, 0.dp, 0.dp),
                   horizontalAlignment = Alignment.End) {
                   Text(text = plantsList[plant].first!!.name,
                       modifier = Modifier
                           .padding(0.dp, 0.dp, 10.dp, 0.dp)
                           .fillMaxWidth(),
                       textAlign = TextAlign.Center)
                   Divider(thickness = 1.dp,
                       modifier = Modifier.padding(0.dp, 0.dp,0.dp, 5.dp))
                   plantsList[plant].first!!.age?.let { it1 ->
                           Text(text = "$it1 days old" ,
                               modifier = Modifier.fillMaxWidth(),
                               textAlign = TextAlign.Center)
                       }
                   plantsList[plant].first!!.type?.let { it1 ->
                           Text(text = it1,
                               modifier = Modifier.fillMaxWidth(),
                               textAlign = TextAlign.Center)
                       }
               }
           }
            }
        }
    }
