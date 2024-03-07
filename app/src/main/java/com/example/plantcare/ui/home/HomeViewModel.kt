package com.example.plantcare.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.domain.repository.PlantsRepository
import com.example.plantcare.domain.repository.TasksRepository
import com.example.plantcare.ui.utils.changeTaskToInactive
import com.example.plantcare.ui.utils.GetDateInMillis
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
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

        //TODO to test if flow should emit instead of being a suspended fun
        viewModelScope.launch(Dispatchers.IO) {
            plantsRepository.getActivePlants(date).collect { map ->
                _uiState.update {
                    it.copy(plantsMap = map, isLoading = false)
                }
            }
        }
    }


    fun updateTask(task: Tasks) = viewModelScope.launch {
        var taskToUpdate = changeTaskToInactive(task, GetDateInMillis())
        tasksRepository.updateTasks(taskToUpdate)
    }
}


