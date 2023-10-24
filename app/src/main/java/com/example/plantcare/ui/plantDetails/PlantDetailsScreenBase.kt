package com.example.plantcare.ui.plantDetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.plantcare.ui.home.HomeViewModel

@Composable
fun PlantDetailsScreenBase() {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        //TODO modify the parameter
        Column(modifier = Modifier.verticalScroll(scrollState)) {
          //  PlantDetailsScreenTop(plants)
          //  PlantDetailsTasksList(plants)
          //  PlantDetailsNotes(plants[3].notes!!)
                TextButton(onClick = { /*TODO*/ },
                        modifier = Modifier.align(Alignment.End)) {
                    Text(text = "edit plant",
                        textAlign = TextAlign.End)
                }
        }
    }
}