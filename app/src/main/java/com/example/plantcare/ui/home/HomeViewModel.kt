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

    // private val _uiState : MutableStateFlow(HomeUiState()) = pla
    //  var uiState = ChangeDataQuery(GetDateInMillis()).map { map ->  }
    private var _uiState = MutableStateFlow(HomeUiState())
    val uiState : StateFlow<HomeUiState> = _uiState

    init {
        changeQuery(GetDateInMillis())

        // Observe for favorite changes in the repo layer
        viewModelScope.launch {
            _uiState = plantsRepository.getActivePlants(GetDateInMillis()).map {
                HomeUiState(plantsMap = it)
            }   .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = HomeUiState(plantsMap = mapOf())
                    )
            plantsRepository.getActivePlants(GetDateInMillis()).collect { map ->
                HomeUiState.update { it.copy(plantsMap = map) }
            }
        }
    }

    fun changeQuery(date: Date) {
        // Ui state is refreshing
        viewModelState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            plantsRepository.getActivePlants(date).collect { map ->
                viewModelState.update { it.copy(plantsMap = map, isLoading = false) }
            }
        }
    }

        //  val uiState = combine(
        //      plantsRepository.getActivePlants(),
        //      plantsRepository.getFutureActivePlants(GetDateInMillis()),
        //  ) { current, future ->
        //      HomeUiState.Queries(
        //          currentPlantsMap = current,
        //          futurePlantsMap = future
        //      )
        //  }
        //      .stateIn(
        //          scope = viewModelScope,
        //          started = SharingStarted.WhileSubscribed(5_000),
        //          initialValue = HomeUiState.Loading
        //      )

          fun ChangeDataQuery(date: Date): StateFlow<HomeUiState> {
              val currentDate = GetDateInMillis()
              var query = plantsRepository.getActivePlants(date)
              if(date != currentDate) {
                  Log.e("HERE", "future ${date.toString()}")
                  query = plantsRepository.getFutureActivePlants(date)
              } else {
                  Log.e("HERE","current ${currentDate.toString()}")
                  query = plantsRepository.getActivePlants()
              }
             // uiState =  query.map {
             //     HomeUiState(plantsMap = it)
             // }   .stateIn(
             //     scope = viewModelScope,
             //     started = SharingStarted.WhileSubscribed(5000),
             //     initialValue = HomeUiState(plantsMap = mapOf()),
              )
              return uiState
          }

        @RequiresApi(Build.VERSION_CODES.O)
        fun updateTask(task: Tasks) = viewModelScope.launch {
            var taskToUpdate = ChangeTaskToInactive(task, GetDateInMillis())
            tasksRepository.updateTasks(taskToUpdate)
        }


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
    }



        //  data class HomeUiState(
        //      var lists :
        //      var currentPlantsMap: Map<Plants?, List<Tasks>>?,
        //      var futurePlantsMap: Map<Plants?, List<Tasks>>?,
        //      val isLoading: Boolean = false
        //  )



//sealed interface HomeUiState {
//    data object Loading : HomeUiState
//
//    data class Queries(
//        var currentPlantsMap: Map<Plants?, List<Tasks>>?,
//        var futurePlantsMap: Map<Plants?, List<Tasks>>?
//    ) : HomeUiState
//
//    data object Empty : HomeUiState
//}