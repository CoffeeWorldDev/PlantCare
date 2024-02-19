package com.example.plantcare.ui.plantDetails

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import com.example.plantcare.ui.plantCreationEdit.PlantDetailsViewModel

@Composable
fun PlantDetails(
    plantId: Long,
    onNavigateToDetail: (String, Long, NavBackStackEntry) -> Unit,
    upPress: () -> Unit,
    viewModel: PlantDetailsViewModel = hiltViewModel()
) {
    viewModel.getPlantWithId(plantId)
    val plantEditCreationUiState by viewModel.uiState.collectAsStateWithLifecycle()
    Log.e("PLANT DETAIL", plantEditCreationUiState.tasks?.toString() ?: "empty")

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