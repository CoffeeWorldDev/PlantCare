package com.example.plantcare.ui.utils

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
fun AddFloatingBtn(modifier: Modifier) {
    FloatingActionButton(onClick = { /*TODO*/ },
        containerColor = Color.White,
        shape = RoundedCornerShape(50.dp),
        modifier = Modifier
            .padding(20.dp, 0.dp)
            .height(50.dp)
            .width(50.dp)
            .offset(0.dp, 20.dp)
    ) {
        Icon(Icons.Filled.Add, "Floating action button.")
    }
}