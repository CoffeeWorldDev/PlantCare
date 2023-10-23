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

    private val _state : StateFlow<PlantsState> = query.map { PlantsState(plantsMap = it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = PlantsState()
        )
    val plantsState : StateFlow<PlantsState>
        get() = _state

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
    data class PlantsState(val plantsMap : Map<Plants?, List<Tasks>>? = mapOf())
}