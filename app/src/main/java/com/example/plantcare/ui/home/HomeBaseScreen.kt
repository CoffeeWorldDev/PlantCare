package com.example.plantcare.ui.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeBaseScreen(viewModel: HomeViewModel = hiltViewModel(),
                   modifier: Modifier = Modifier) {
    val homeUiState by viewModel.uiState.collectAsStateWithLifecycle()
    Log.e("HOME BASE", homeUiState.toString())


    Column() {
        HomeTop(
            onValueChange = { viewModel.changeQuery(it) },
            modifier = Modifier
        )
    }

        HomeMainBlock(homeUiState.plantsMap,
                      onTaskClicked = { viewModel.updateTask(it)})
    }
// fun HomeBaseScreen(
//    onDateChanged: (Date) -> Unit,

