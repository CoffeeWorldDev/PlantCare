package com.example.plantcare.ui.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import java.util.Date

//TODO make an alternative for older versions really
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeBaseScreen(viewModel: HomeViewModel = hiltViewModel(),
                   modifier: Modifier = Modifier) {

    val plantsState by viewModel.uiState.collectAsState()
    Log.e("HERE", plantsState.plantsMap.toString())

    Column() {
        HomeTop(onValueChange = {viewModel.onDataChange(it)},
                modifier = Modifier)

        HomeMainBlock(plantsState.plantsMap,
                      onTaskClicked = { viewModel.updateTask(it)})
    }
}
// fun HomeBaseScreen(
//    onDateChanged: (Date) -> Unit,

