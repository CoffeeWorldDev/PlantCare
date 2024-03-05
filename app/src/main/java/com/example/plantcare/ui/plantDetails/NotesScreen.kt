package com.example.plantcare.ui.plantDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
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
import com.example.plantcare.R
import com.example.plantcare.ui.components.PlantCareIconButton
import com.example.plantcare.ui.navigation.PlantSections

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
    notes: Map<String, String>?,
    //showDialog : Boolean,
    closeDialog : () -> Unit,
    save : () -> Unit
) {
    Dialog(
        onDismissRequest = {closeDialog()},
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier.fillMaxSize(0.97f)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                PlantCareIconButton(
                    iconImage = Icons.Filled.Close,
                    contentDescription = R.string.close_btn,
                    onClick = { closeDialog() }
                )
                Text(
                    text = "Notes"
                )
                TextButton(
                    onClick = { /*TODO*/ }
                ) {
                    Text("save")
                }
            }
            TextField(
                modifier = Modifier.fillMaxSize(),
                //readOnly = true,
                value = "currentDate",
                onValueChange = {},
            )
        }
    }
}