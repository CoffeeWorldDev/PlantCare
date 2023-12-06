package com.example.plantcare.ui.plantCreationEdit

import androidx.compose.runtime.Composable
import com.example.plantcare.data.model.Plants


@Composable
fun PlantCreationEditTasksList(plant: Plants){
//    var currentSeason by remember { mutableStateOf(plant.season) }
//    var activeTasks : MutableList<Tasks> = mutableListOf()
//    Log.e("HERE", currentSeason)
//    plant.tasks!!.forEach{
//        if (it.season == currentSeason){
//            activeTasks.add(it)
//        }
//    }
//    Box(modifier = Modifier
//        .height(300.dp)
//        .fillMaxWidth(),
//        contentAlignment = Alignment.BottomEnd){
//        Column(modifier = Modifier,
//            verticalArrangement = Arrangement.SpaceEvenly,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(
//                text = "Tasks:",
//                style = MaterialTheme.typography.titleLarge,
//                modifier = Modifier
//                    .padding(10.dp, 20.dp, 0.dp, 5.dp)
//                    .fillMaxWidth())
//            Card(shape = CardDefaults.elevatedShape,
//                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
//                elevation = CardDefaults.cardElevation(
//                    defaultElevation = 8.dp
//                ),
//                border = BorderStroke(1.dp, Color.Black),
//                modifier = Modifier
//                    .fillMaxWidth(0.96f)
//                    .fillMaxHeight()) {
//                SeasonsSegmentedButton(modifier = Modifier, plant.season) { currentSeason = it}
//                if (plant.tasks!!.isNotEmpty()){
//                    ReadOnlyTaskList(activeTasks)
//                } else{
//                    EmptyListMsg("You haven't set any\ntasks for this plant yet")
//                }
//            }
//
//        }
//
//        FloatingActionButton(onClick = { /*TODO*/ },
//            containerColor = Color.White,
//            shape = RoundedCornerShape(50.dp),
//            modifier = Modifier
//                .padding(20.dp, 0.dp)
//                .height(50.dp)
//                .width(50.dp)
//                .offset(0.dp, 20.dp)
//        ) {
//            Icon(Icons.Filled.Add, "Floating action button.")
//        }
//    }
}

//@Composable
//fun SeasonsSegmentedButton(currentSeason : String){
//    var currentSeason by remember { mutableStateOf(currentSeason)}
//    val options = listOf("summer", "winter", "dormant")
//
//}

