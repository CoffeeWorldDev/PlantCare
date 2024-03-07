package com.example.plantcare.ui.plantDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.plantcare.R
import com.example.plantcare.ui.components.PlantCareIconButton

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
    notes: String?,
    //showDialog : Boolean,
    closeDialog: () -> Unit,
    onSave: (String) -> Unit
) {
    val notes = remember(notes) { mutableStateOf(notes) }

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
                    onClick = {
                        onSave(notes.value.toString())
                        closeDialog()
                    }
                ) {
                    Text("save")
                }
            }
            TextField(
                modifier = Modifier.fillMaxSize(),
                //readOnly = true,
                value = notes.value ?: "",
                onValueChange = {
                    notes.value = it
                    //onSave(it)
                },
            )
        }
    }
}