package com.example.plantcare.ui.plantDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.plantcare.R
import com.example.plantcare.ui.components.PlantCareIconButton

@Composable
fun NotesDialog(
    notes: String?,
    closeDialog: () -> Unit,
    onSave: (String) -> Unit
) {
    val notesValue = remember(notes) { mutableStateOf(notes) }

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
                        onSave(notesValue.value.toString())
                        closeDialog()
                    }
                ) {
                    Text("save")
                }
            }
            TextField(
                modifier = Modifier.fillMaxSize(),
                //readOnly = true,
                value = notesValue.value ?: "",
                onValueChange = {
                    notesValue.value = it
                    //onSave(it)
                },
            )
        }
    }
}