package com.example.plantcare.ui.plantsGallery

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plantcare.R
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.ui.components.PlantCareImage
import com.example.plantcare.ui.utils.getElapsedTime

@Composable
fun PlantsGalleryBodyVerL1(plants: Map<Plants?, List<Tasks>>?,
                           onPlantClick: (Long) -> Unit,
                           modifier: Modifier) {
    val plantsList = plants?.toList()
    val headlineLarge = MaterialTheme.typography.headlineLarge
    LazyColumn(){ items(plantsList!!.size) {
        Row(modifier = Modifier
            .padding(15.dp, 0.dp, 0.dp, 20.dp)
            .clickable { onPlantClick(plantsList[it].first!!.plantsId) }) {
            PlantCareImage(imageUrl = plantsList[it].first!!.photo!!,
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
                Text(text = plantsList[it].first!!.name,
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 10.dp, 0.dp)
                        .fillMaxWidth(),
                    style = headlineLarge,
                    textAlign = TextAlign.Center)
                Divider(thickness = 1.dp,
                    modifier = Modifier.padding(0.dp, 0.dp,0.dp, 10.dp))
                Row(horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 10.dp, 0.dp)
                        .fillMaxWidth()) {
                    plantsList[it].first!!.age?.let { it1 ->
                        Text(text = "${getElapsedTime(it1)} days old" ,
                             modifier = Modifier)
                    }
                    plantsList[it].first!!.type?.let { it1 ->
                        Text(text = it1,
                             modifier = Modifier)
                    }
                }
            }
        }
        if(it == plantsList!!.size-1){
            Spacer(modifier = Modifier.padding(0.dp, 0.dp,0.dp, 35.dp))
        }
    }
    }
}