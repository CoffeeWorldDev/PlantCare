package com.example.plantcare.ui.plantsGallery

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.plantcare.R
import com.example.plantcare.data.model.Plants
import com.example.plantcare.ui.components.PlantCareImage
import com.example.plantcare.ui.utils.getElapsedTime


@Composable
fun PlantsGalleryBodyVerL1(
    plants: List<Plants>,
    onPlantClick: (Long) -> Unit
) {
    val headlineLarge = MaterialTheme.typography.headlineLarge
    LazyColumn { items(plants.size) {
        Row(
            modifier = Modifier
            .padding(start = 15.dp, bottom = 20.dp)
            .clickable(
                onClick = {
                    onPlantClick(
                        plants[it].plantsId
                    )
                }
            )
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
                .padding( start = 10.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = plants[it].name,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .fillMaxWidth(),
                    style = headlineLarge,
                    textAlign = TextAlign.Center
                )
                Divider(
                    thickness = 1.dp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "${getElapsedTime(plants[it].age)} days old" ,
                        modifier = Modifier
                    )
                    Text(
                        text = plants[it].type,
                        modifier = Modifier
                    )
                }
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