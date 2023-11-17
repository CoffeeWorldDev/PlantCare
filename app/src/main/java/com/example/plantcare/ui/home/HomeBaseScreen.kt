package com.example.plantcare.ui.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

//TODO make an alternative for older versions really
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeBaseScreen(viewModel: HomeViewModel = hiltViewModel(),
                   modifier: Modifier = Modifier) {
    val plantsState by viewModel.uiState.collectAsStateWithLifecycle()
    Log.e("HERE", plantsState.toString())


    Column() {
        HomeTop(
            onValueChange = { viewModel.changeQuery(it) },
            modifier = Modifier
        )
    }

        HomeMainBlock(plantsState.plantsMap,
                      onTaskClicked = { viewModel.updateTask(it)})
    }
// fun HomeBaseScreen(
//    onDateChanged: (Date) -> Unit,

