package com.example.plantcare.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.domain.repository.PlantsRepository
import com.example.plantcare.domain.repository.TasksRepository
import com.example.plantcare.ui.utils.changeTaskToInactive
import com.example.plantcare.ui.utils.getDateInMillis
import com.example.plantcare.worker.Initializer
import com.example.plantcare.worker.TasksWorker
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
    private var workerInitializer: Initializer
    //savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

   // private val workManager = WorkManager.getInstance(application)

    init {
        changeQuery(getDateInMillis())
        //workerInitializer()
    }

    fun addDatePeriodicWorker(context: Context) {
        val workRequest = OneTimeWorkRequestBuilder<TasksWorker>().build()
        WorkManager.getInstance(context).enqueue(workRequest)
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
        var taskToUpdate = changeTaskToInactive(task, getDateInMillis())
        tasksRepository.updateTasks(taskToUpdate)
    }
}


