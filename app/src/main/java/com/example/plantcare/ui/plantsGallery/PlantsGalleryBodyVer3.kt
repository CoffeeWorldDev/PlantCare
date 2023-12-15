package com.example.plantcare.ui.plantsGallery

import androidx.compose.runtime.Composable
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks

@Composable
fun PlantsGalleryBodyVerL3(
    plants: Map<Plants?, List<Tasks>>?,
    columns: Int,
   //onNavigateToSecondScreen: (String) -> Unit
) {
 //   val plantsList = plants?.toList()
 //   LazyVerticalGrid(columns = GridCells.Fixed(columns),
 //       modifier = Modifier.fillMaxSize()){
 //       items(plantsList!!.size) {plant ->
 //           Column(modifier = Modifier) {
 //               Image(painter = painterResource(id = R.drawable.baseline_image_24),
 //                   contentDescription = "plant image",
 //                   modifier = Modifier
 //                       .fillMaxWidth()
 //                       .height(180.dp)
 //               )
 //               if(columns < 3)
 //                   Text(text = plantsList[it].first!!.name,
 //                       modifier = Modifier
 //                           .fillMaxWidth(),
 //                       textAlign = TextAlign.Center)
 //               }
 //           }
 //       }
    }
