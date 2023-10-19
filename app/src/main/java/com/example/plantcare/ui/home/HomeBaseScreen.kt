package com.example.plantcare.ui.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import java.util.Date

@Composable
fun HomeBaseScreen(viewModel: HomeViewModel = hiltViewModel(),
                   modifier: Modifier = Modifier) {

    val plantsState by viewModel.plantsState.collectAsState()
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

