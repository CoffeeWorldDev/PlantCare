package com.example.plantcare.ui.composable.plantDetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.plantcare.data.model.Plants
import com.example.plantcare.ui.composable.PlantDetailsNotes
import com.example.plantcare.ui.composable.PlantDetailsTasksList
import com.example.plantcare.ui.composable.getPlants

@Composable
fun PlantDetailsScreenBase(plants: List<Plants>) {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier.fillMaxSize(),
 //       contentAlignment = Alignment.Center
    ){
        //TODO modify the parameter
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            PlantDetailsScreenTop(getPlants())
            PlantDetailsTasksList(getPlants())
            PlantDetailsNotes(getPlants()[3].notes!!)
        }
    }
}