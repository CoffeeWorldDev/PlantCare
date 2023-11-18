package com.example.plantcare.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantcare.R
import com.example.plantcare.data.Results
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.data.util.Async
import com.example.plantcare.domain.repository.PlantsRepository
import com.example.plantcare.domain.repository.TasksRepository
import com.example.plantcare.ui.util.ChangeTaskToInactive
import com.example.plantcare.ui.util.ErrorMessage
import com.example.plantcare.ui.util.GetDateInMillis
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID
import javax.inject.Inject

data class HomeUiState(
    val plantsMap: Map<Plants?, List<Tasks>>? = null,
    val isLoading: Boolean = false,
    val userErrorMessage: Int? = null
)
@HiltViewModel
class HomeViewModel @Inject constructor (
    private val plantsRepository: PlantsRepository,
    private val tasksRepository: TasksRepository,
    //savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        changeQuery(GetDateInMillis())
    }

    fun changeQuery(date: Date) {
        // Ui state is refreshing
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            plantsRepository.getActivePlants(date).collect { map ->
                _uiState.update {
                    it.copy(plantsMap = map, isLoading = false)
                }
            }
        }
    }


    fun updateTask(task: Tasks) = viewModelScope.launch {
        var taskToUpdate = ChangeTaskToInactive(task, GetDateInMillis())
        tasksRepository.updateTasks(taskToUpdate)
    }
}


