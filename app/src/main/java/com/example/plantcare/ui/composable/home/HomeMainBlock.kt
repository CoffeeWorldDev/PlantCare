package com.example.plantcare.ui.composable.home

import androidx.compose.runtime.Composable
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks


@Composable
fun HomeMainBlock(plants: Map<Plants, List<Tasks>>){
//    val primaryColor = MaterialTheme.colorScheme.primary
//    val onPrimaryColor = MaterialTheme.colorScheme.onPrimary
//    val shape = RoundedCornerShape(topStartPercent = 50, bottomStartPercent = 50)
//    val headlineSmall = MaterialTheme.typography.headlineSmall
//    //val plantsList = remember(plants)
//    LazyColumn(){ items(plants.size) {
//        Row(modifier = Modifier.padding(15.dp, 0.dp,0.dp, 20.dp)) {
//            Image(painter = painterResource(id = R.drawable.baseline_image_24),
//                  contentDescription = "plant image",
//                  modifier = Modifier.border(1.dp, color = Color.Black)
//                                     .weight(1.1f)
//                                     .height(95.dp)
//                          )
//            Column(modifier = Modifier.weight(3f)
//                                      .padding(10.dp, 0.dp,0.dp, 0.dp),
//                   horizontalAlignment = Alignment.End) {
//                Text(text = plants[it].name,
//                     modifier = Modifier.padding(0.dp, 0.dp,10.dp, 0.dp),
//                     style = headlineSmall)
//                Divider(thickness = 1.dp,
//                        modifier = Modifier.padding(7.dp, 0.dp,0.dp, 10.dp))
//                plants[it].tasks?.forEach {
//                    Card(shape = shape,
//                        colors = CardDefaults.cardColors(containerColor = primaryColor),
//                       elevation = CardDefaults.cardElevation(
//                           defaultElevation = 5.dp
//                       ),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(0.dp, 2.dp)
//                            .shadow(8.dp, shape)
//                    ) {
//                        Text(
//                            text = it.name,
//                            color = onPrimaryColor,
//                            textAlign = TextAlign.Center,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(0.dp, 2.dp)
//                                .wrapContentSize(Alignment.Center),
//                        )
//                    }
//                }
//            }
//        }
//    }
//
//    }
}
