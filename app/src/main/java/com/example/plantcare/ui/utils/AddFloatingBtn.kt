package com.example.plantcare.ui.utils

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddFloatingBtn(
    onClick: () -> Unit,
    modifier: Modifier) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Color.White,
        shape = RoundedCornerShape(50.dp),
        modifier = modifier
    ) {
        Icon(Icons.Filled.Add, "Floating action button.")
    }
}