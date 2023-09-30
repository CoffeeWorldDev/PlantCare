package com.example.plantcare.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.ui.composable.util.EmptyListMsg


@Composable
fun PlantDetailsTasksList(plant: List<Plants>) {

 //   Box(modifier = Modifier
 //       .height(300.dp)
 //       .fillMaxWidth()){
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tasks:",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(10.dp, 20.dp, 0.dp, 5.dp)
                .fillMaxWidth())
        Card(shape = CardDefaults.elevatedShape,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .fillMaxWidth(0.96f)
                .fillMaxHeight()) {
                if (plant[3].tasks!!.isNotEmpty()){
                    TasksList(plant[3].tasks!!)
                } else{
                    EmptyListMsg("You haven't set any\ntasks for this plant yet")
                }
        }
        FloatingActionButton(onClick = { /*TODO*/ },
            containerColor = Color.White,
            modifier = Modifier
                .align(Alignment.End)
                .padding(10.dp)
                .height(50.dp)
                .width(50.dp)
                //.weight(0.5f)
                //.offset(0.dp, 10.dp)
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }
    }
  //  }
}

@Composable
fun TasksList(tasks: List<Tasks>){
    //TODO check if it has been done on the same day and disable
    //TODO function to change lastDone date
    //TODO check for buttons text length
    //TODO color based on urgency
    //TODO days/day/tomorrow/today fun

    LazyColumn(modifier = Modifier
       // .fillMaxHeight(0.80f)
    ){
        items(tasks.size){
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                       onClick = { /*TODO*/ },
                        modifier = Modifier
                            .weight(1.5f)
                            .padding(10.dp, 10.dp)) {
                        Text(text = tasks[it].name,
                            textAlign = TextAlign.Center)
                }
                Text(
                    text = "last time was:\n${tasks[it].daysUncompleted} days ago",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp, 10.dp))
                Text(
                    text = "next time will be:\nin ${tasks[it].daysUntilNextCycle} days",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp, 10.dp))
            }
            Divider(thickness = 1.dp,
                modifier = Modifier.padding(7.dp, 2.dp))
        }
    }
}

