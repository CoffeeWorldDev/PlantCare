package com.example.plantcare.ui.plantDetails

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

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