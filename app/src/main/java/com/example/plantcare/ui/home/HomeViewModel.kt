package com.example.plantcare.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.domain.useCase.plants.AddPlantsUseCase
import com.example.plantcare.domain.useCase.plants.GetPlantsAndTasksUseCase
import com.example.plantcare.domain.useCase.plants.GetActivePlantsUseCase
import com.example.plantcare.domain.useCase.plants.GetFutureActivePlantsUseCase
import com.example.plantcare.domain.useCase.tasks.UpdateTasksUseCase
import com.example.plantcare.ui.util.ChangeTaskToInactive
import com.example.plantcare.ui.util.getDateInMillis
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor (private val getActivePlantsUseCase: GetActivePlantsUseCase,
                                         private val updateTasksUseCase: UpdateTasksUseCase,
                                         private val getFutureActivePlantsUseCase: GetFutureActivePlantsUseCase) : ViewModel() {

    var query = getActivePlantsUseCase.execute()

    private val _uiState : StateFlow<homeUiState> = query.map { homeUiState(plantsMap = it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = homeUiState()
        )
    val uiState : StateFlow<homeUiState>
        get() = _uiState

    fun getPlants(): Flow<Map<Plants?, List<Tasks>>?> {
        return getActivePlantsUseCase.execute()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateTask(task: Tasks) = viewModelScope.launch{
        var taskToUpdate = ChangeTaskToInactive(task, getDateInMillis())
        updateTasksUseCase.execute(taskToUpdate)
    }

    fun onDataChange(date: Date){
        if(date == getDateInMillis()){
            query = getActivePlantsUseCase.execute()
        } else {
            query = getFutureActivePlantsUseCase.execute(date)
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
    data class homeUiState(var plantsMap : Map<Plants?, List<Tasks>>? : getActivePlantsUseCase)
}