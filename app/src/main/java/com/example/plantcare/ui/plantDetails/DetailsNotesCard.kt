package com.example.plantcare.ui.plantDetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.plantcare.ui.utils.AddFloatingBtn
import com.example.plantcare.ui.utils.EmptyListMsg

@Composable
fun PlantDetailsNotes(
    notes: String,
    showDialog: () -> Unit,
    modifier: Modifier
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Notes:",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(10.dp, 20.dp, 0.dp, 5.dp)
                .fillMaxWidth()
        )
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.BottomEnd
        ) {
            Card(
                shape = CardDefaults.elevatedShape,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                ),
                border = BorderStroke(1.dp, Color.Black),
                modifier = Modifier
                    .fillMaxWidth(0.96f)
                    .fillMaxHeight()
            ) {
                if (notes.isNotEmpty()) {
                    NotesList(notes)
                }else {
                    EmptyListMsg(stringResource(id = R.string.empty_notes_list))
                }
            }
            AddFloatingBtn(
                onClick = {
                    showDialog()
                },
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .height(50.dp)
                    .width(50.dp)
                    .offset(0.dp, 20.dp))
        }
    }
}

@Composable
fun NotesList(notes: String) {
    Column(
        modifier = Modifier
        .verticalScroll(rememberScrollState())
    ){
        Text(
            text = notes,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(15.dp, 10.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))
    }
}