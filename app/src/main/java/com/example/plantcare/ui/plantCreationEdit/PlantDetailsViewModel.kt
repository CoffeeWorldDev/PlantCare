package com.example.plantcare.ui.plantCreationEdit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.domain.repository.PlantsRepository
import com.example.plantcare.domain.repository.TasksRepository
import com.example.plantcare.ui.utils.getDateInMillis
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PlantCreationEditUiState(
    val plant: Plants = Plants(0, "", "", "", "", getDateInMillis(), "",
        "", "summer"),
    val tasks: List<Tasks>? = emptyList(),
    val photo : String = "",
    val isLoading: Boolean = false,
    val isEdit: Boolean = false,
    val userErrorMessage: Int? = null,
)

@HiltViewModel
class PlantDetailsViewModel @Inject constructor (
    private val plantsRepository: PlantsRepository,
    private val tasksRepository: TasksRepository,
    //savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _uiState = MutableStateFlow(PlantCreationEditUiState(isLoading = true))
    //: StateFlow<PlantCreationEditUiState> down
    val uiState = _uiState
    //    .stateIn(
    //            scope = viewModelScope,
    //            started = SharingStarted.WhileSubscribed(),
    //            initialValue = _uiState.value
    //        )
    .asStateFlow()
    fun getPlantWithId(plantId: Long) {
        _uiState.update { it.copy(isLoading = true) }
        if (plantId.toInt() != -1) {
            viewModelScope.launch {
                plantsRepository.getPlantsFromId(plantId).collect { map ->
                    if (map!!.isNotEmpty()) {
                        _uiState.update { state ->
                            state.copy(
                                plant = map.keys.first()!!,
                                tasks = map.values.first(),
                                photo = map.keys.first()!!.photo!!,
                                isEdit = true
                            )
                        }
                    }
                }
            }
        }
        _uiState.update { it.copy(isLoading = false) }
    }

    //TESTING PURPOSE ONLY
    fun getAllTasks() : MutableList<Tasks>{
        val list = mutableListOf<Tasks>()
        viewModelScope.launch {
            tasksRepository.getAllTasks().collect{
                if(it?.isNotEmpty() == true){
                    it.forEach{task ->
                        list.add(task)
                    }
                }
            }
        }
        return list
    }

    fun savePlant(plant : Plants){
        viewModelScope.launch {
            if(uiState.value.isEdit){
                plantsRepository.updatePlants(plant)
            } else {
                val id =  plantsRepository.addPlants(plant)
                _uiState.value.tasks?.forEach{
                    it.ownerPlantId = id
                    //TODO prepare task to save
                    tasksRepository.addTasks(it)
                }
            }
        }
    }
    fun createTask(task : Tasks){
        viewModelScope.launch {
            if(uiState.value.isEdit){
                tasksRepository.addTasks(task)
            } else {
                createTempTasks(task)
            }
        }
    }
    fun updateTask(
        task : Tasks,
        index: Int
    ){
        viewModelScope.launch {
            if(uiState.value.isEdit){
                tasksRepository.updateTasks(task)
            } else {
                _uiState.update {
                    it.copy(
                        tasks = it.tasks!!.filterIndexed { i, _ ->  i != index}
                    )
                }
                createTempTasks(task)
            }
        }
    }

    fun deleteTask(
        task : Tasks,
        index: Int
    ){
        viewModelScope.launch {
            if(uiState.value.isEdit){
                tasksRepository.deleteTasks(task)
            } else {
                _uiState.update {
                    it.copy(
                        tasks = it.tasks!!.filterIndexed { i, _ ->  i != index}
                    )
                }
            }
        }
    }
    private fun createTempTasks(tasks: Tasks){
        _uiState.update {
            it.copy(
                tasks = it.tasks!!.plus(tasks)
            )
        }
    }

    fun deletePlant(plant : Plants){
        viewModelScope.launch(Dispatchers.IO) {
            plantsRepository.deletePlants(plant)
        }
    }
}


