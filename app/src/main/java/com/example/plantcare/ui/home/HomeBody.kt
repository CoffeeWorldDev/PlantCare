package com.example.plantcare.ui.home

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.ui.components.PlantCareImage


@Composable
fun HomeMainBlock(
    plants: Map<Plants?, List<Tasks>>?,
    onPlantClick: (String, Long) -> Unit,
    onTaskClicked: (Tasks) -> Unit
){
    //modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
    val plantsList = plants?.toList()
    val primaryColor = MaterialTheme.colorScheme.primary
    val onPrimaryColor = MaterialTheme.colorScheme.onPrimary
    val shape = RoundedCornerShape(topStartPercent = 50, bottomStartPercent = 50)
    val headlineSmall = MaterialTheme.typography.headlineSmall

    LazyColumn(){
        if (plantsList != null) {
            items(plantsList.size) {
            Row(modifier = Modifier.padding(15.dp, 0.dp,0.dp, 20.dp)) {
                PlantCareImage(
                    imageUrl = plantsList[it].first!!.photo ?: R.drawable.placeholderimage,
                    contentDescription = stringResource(id = R.string.missing_photo_description),
                    modifier = Modifier
                        .border(1.dp, color = Color.Black)
                        .weight(1.2f)
                        .height(95.dp)
                )
              //  Image(painter = painterResource(id = R.drawable.baseline_image_24),
              //        contentDescription = "plant image",
              //        modifier = Modifier
              //            .border(1.dp, color = Color.Black)
              //            .weight(1.1f)
              //            .height(95.dp)
              //                )
                Column(modifier = Modifier
                    .weight(3f)
                    .padding(10.dp, 0.dp, 0.dp, 0.dp),
                       horizontalAlignment = Alignment.End) {
                    Text(text = plantsList[it].first!!.name,
                         modifier = Modifier.padding(0.dp, 0.dp,10.dp, 0.dp),
                         style = headlineSmall)
                    Divider(thickness = 1.dp,
                            modifier = Modifier.padding(7.dp, 0.dp,0.dp, 10.dp))
                    plantsList[it].second.forEach {
                        Card(shape = shape,
                             colors = CardDefaults.cardColors(containerColor = primaryColor),
                             elevation = CardDefaults.cardElevation(
                                          defaultElevation = 5.dp
                             ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 2.dp)
                                //.shadow(8.dp, shape)
                                .clickable { onTaskClicked(it) }
                        ) {
                            Text(
                                text = it.name,
                                color = onPrimaryColor,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp, 2.dp)
                                    .wrapContentSize(Alignment.Center),
                            )
                        }
                    }
                }
            }
        }

            }
        }
}