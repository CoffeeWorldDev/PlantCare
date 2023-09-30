package com.example.plantcare.ui.composable.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.plantcare.data.model.Plants

@Composable
fun HomeBaseScreen(plants: List<Plants>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .wrapContentSize(align = Alignment.TopCenter)
    ) {
        HomeTop(modifier = Modifier)
        HomeMainBlock(plants)

    }
}

