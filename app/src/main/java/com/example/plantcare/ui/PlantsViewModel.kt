package com.example.plantcare.ui

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.domain.useCase.plants.AddPlantsUseCase
import com.example.plantcare.domain.useCase.plants.GetPlantsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PlantsViewModel(private val addPlantsUseCase: AddPlantsUseCase,
                      private val getPlantsUseCase: GetPlantsUseCase,) : ViewModel() {

//    private var _state = MutableStateFlow<Map<Plants, List<Tasks>>>(mapOf())
//
//
//    val state: StateFlow<Map<Plants, List<Tasks>>>
//        get() = _state


    val plantsState: StateFlow<PlantsState> = getPlantsUseCase.execute().map { PlantsState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = PlantsState()
        )


    fun addPlant(plant: Plants) = viewModelScope.launch {
        addPlantsUseCase.execute(plant)
    }

//     fun getPlants() = viewModelScope.launch {
//        getPlantsUseCase.execute().collect(){
//           _state.value = it
//        }
//    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
    data class PlantsState(val plantsList: Map<Plants, List<Tasks>> = mapOf())
}