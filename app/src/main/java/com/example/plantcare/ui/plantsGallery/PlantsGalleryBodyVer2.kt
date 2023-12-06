package com.example.plantcare.ui.plantsGallery

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks

@Composable
fun PlantsGalleryMainBlockL2(
    plants: Map<Plants?, List<Tasks>>?,
    columns: Int,
    //onNavigateToSecondScreen: (String) -> Unit
) {
    val plantsList = plants?.toList()
    //var plantsList : Set<Plants> = plants
    LazyVerticalGrid(columns = GridCells.Fixed(columns),
                     modifier = Modifier.fillMaxSize()){
        items(plantsList!!.size) {plant ->
 //          Row(modifier = Modifier.padding(15.dp, 0.dp,0.dp, 20.dp)
 //              .clickable { onNavigateToSecondScreen(plants[plant])
 //              Log.d("ERROR", plants[plant].plantsId.toString())}) {
 //              Card {

 //              }
 //              Image(painter = painterResource(id = R.drawable.baseline_image_24),
 //                  contentDescription = "plant image",
 //                  modifier = Modifier
 //                      .border(1.dp, color = Color.Black)
 //                      .weight(1.1f)
 //                      .height(95.dp)
 //              )
 //              Column(modifier = Modifier
 //                  .weight(3f)
 //                  .padding(10.dp, 0.dp, 0.dp, 0.dp),
 //                  horizontalAlignment = Alignment.End) {
 //                  Text(text = plants[plant].name,
 //                      modifier = Modifier
 //                          .padding(0.dp, 0.dp, 10.dp, 0.dp)
 //                          .fillMaxWidth(),
 //                      textAlign = TextAlign.Center)
 //                  Divider(thickness = 1.dp,
 //                      modifier = Modifier.padding(0.dp, 0.dp,0.dp, 5.dp))
 //                      plants[plant].age?.let { it1 ->
 //                          Text(text = "$it1 days old" ,
 //                              modifier = Modifier.fillMaxWidth(),
 //                              textAlign = TextAlign.Center)
 //                      }
 //                      plants[plant].type?.let { it1 ->
 //                          Text(text = it1,
 //                              modifier = Modifier.fillMaxWidth(),
 //                              textAlign = TextAlign.Center)
 //                      }
 //              }
 //          }
            }
        }
    }
