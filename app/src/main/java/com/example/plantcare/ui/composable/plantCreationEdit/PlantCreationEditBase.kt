package com.example.plantcare.ui.composable.plantCreationEdit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.plantcare.data.model.Plants
import androidx.compose.ui.unit.dp


@Composable
fun PlantCreationEditBase(plants: List<Plants>) {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            PlantCreationEditForm(plants[3])
            PlantCreationEditTasksList(plants[3])
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}