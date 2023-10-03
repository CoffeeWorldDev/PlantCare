package com.example.plantcare.ui.composable.util

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.plantcare.data.model.Tasks

@Composable
fun ReadOnlyTaskList(tasks: List<Tasks>){
    LazyColumn(modifier = Modifier
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
            if(it < tasks.size-1) {
                Divider(
                    thickness = 1.dp,
                    modifier = Modifier.padding(7.dp, 2.dp)
                )
            } else {
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}