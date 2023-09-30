package com.example.plantcare.ui.composable.plantsGallery

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.plantcare.R
import com.example.plantcare.data.model.Plants

@Composable
fun PlantsGalleryMainBlockL2(
    plants: List<Plants>,
    columns: Int,
    onNavigateToSecondScreen: (String) -> Unit
) {
    LazyVerticalGrid(columns = GridCells.Fixed(columns),
                     modifier = Modifier.fillMaxSize()){
        items(plants.size) {plant ->
            Row(modifier = Modifier.padding(15.dp, 0.dp,0.dp, 20.dp)
                .clickable { onNavigateToSecondScreen(plants[plant].plantsId.toString())
                Log.d("ERROR", plants[plant].plantsId.toString())}) {
                Card {

                }
                Image(painter = painterResource(id = R.drawable.baseline_image_24),
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
                    Text(text = plants[plant].name,
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 10.dp, 0.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center)
                    Divider(thickness = 1.dp,
                        modifier = Modifier.padding(0.dp, 0.dp,0.dp, 5.dp))
                        plants[plant].age?.let { it1 ->
                            Text(text = "$it1 days old" ,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center)
                        }
                        plants[plant].type?.let { it1 ->
                            Text(text = it1,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center)
                        }
                }
            }
            }
        }
    }
