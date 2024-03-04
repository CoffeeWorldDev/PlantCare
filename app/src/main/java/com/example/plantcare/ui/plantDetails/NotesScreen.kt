package com.example.plantcare.ui.plantDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun NotesScreen(
    plantId: Long,
    upPress: () -> Unit
) {
    Text(
        text = plantId.toString(),
        textAlign = TextAlign.Start,
        modifier = Modifier.width(80.dp)
    )
}

@Composable
fun NotesDialog(
    notes: Map<String, String>,
    showDialog : Boolean
) {
    var showDialog by remember { mutableStateOf(showDialog) }
 //   Column {
 //       Button(onClick = { showDialog = true }) {
 //           Text(text = "Click to Show Full Screen Dialog" )
 //       }
 //   }
    if (showDialog) {
        Dialog(onDismissRequest = {showDialog = false}, properties = DialogProperties(usePlatformDefaultWidth = false)) {
            // Custom layout for the dialog
            Surface(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(0.dp),
                color = Color.Yellow
            ) {
                Column(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("This is a full screen dialog")
                    Button(
                        onClick = {showDialog = false},
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Close Dialog")
                    }
                }
            }
        }
    }
}