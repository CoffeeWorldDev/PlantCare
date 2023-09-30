package com.example.plantcare.ui.composable.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmptyListMsg(message : String){
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxHeight()){
        Text(
            text = message,
            textAlign = TextAlign.Center,
            lineHeight = 40.sp,
            fontSize = 30.sp,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth())
    }
}