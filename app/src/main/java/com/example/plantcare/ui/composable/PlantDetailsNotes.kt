package com.example.plantcare.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.ui.composable.util.EmptyListMsg

@Composable
fun PlantDetailsNotes(notes: Map<String, String>){
    val scrollState = rememberScrollState()
    Column(modifier = Modifier
        .height(300.dp)
        .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Notes:",
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
                .fillMaxHeight()
               // .verticalScroll(scrollState)
        ) {
            if (notes!!.isNotEmpty()){
                    NotesList(notes)

            } else{
                EmptyListMsg("You haven't saved any\nnotes for this plant yet")
            }
        }
    }
}


@Composable
fun NotesList(notes: Map<String, String>) {
    Column( modifier = Modifier
        .verticalScroll(rememberScrollState()) ){
    notes.forEach {
        Column(verticalArrangement = Arrangement.Top,) {
            Text(
                text = "${it.key}\n\n${it.value}",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(15.dp, 10.dp)
                    .fillMaxWidth()
            )
            Divider(
                thickness = 1.dp,
                modifier = Modifier.padding(7.dp, 2.dp)
            )
        }
    }
    }
}