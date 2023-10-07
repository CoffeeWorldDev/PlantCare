package com.example.plantcare.ui.composable.plantDetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks


@Composable
fun PlantDetailsTasksList(plant: State<Map<Plants, List<Tasks>>>) {

//    Box(modifier = Modifier
//        .height(300.dp)
//        .fillMaxWidth(),
//        contentAlignment = Alignment.BottomEnd){
//    Column(modifier = Modifier,
//        verticalArrangement = Arrangement.SpaceEvenly,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "Tasks:",
//            style = MaterialTheme.typography.titleLarge,
//            modifier = Modifier
//                .padding(10.dp, 20.dp, 0.dp, 5.dp)
//                .fillMaxWidth())
//        Card(shape = CardDefaults.elevatedShape,
//            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
//            elevation = CardDefaults.cardElevation(
//                defaultElevation = 8.dp
//            ),
//            border = BorderStroke(1.dp, Color.Black),
//            modifier = Modifier
//                .fillMaxWidth(0.96f)
//                .fillMaxHeight()) {
//                if (plant[3].tasks!!.isNotEmpty()){
//                    ReadOnlyTaskList(plant[3].tasks!!)
//                } else{
//                    EmptyListMsg("You haven't set any\ntasks for this plant yet")
//                }
//
//        }
//
//    }
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

@Composable
fun TasksList(tasks: List<Tasks>){
    //TODO check if it has been done on the same day and disable
    //TODO function to change lastDone date
    //TODO check for buttons text length
    //TODO color based on urgency
    //TODO days/day/tomorrow/today fun


}

