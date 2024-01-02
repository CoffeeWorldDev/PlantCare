package com.example.plantcare.ui.plantCreationEdit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.domain.repository.PlantsRepository
import com.example.plantcare.domain.repository.TasksRepository
import com.example.plantcare.ui.utils.GetDateInMillis
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PlantCreationEditUiState(
    //val plant: Map<Plants?, List<Tasks>>? = null,
    val plant: Plants = Plants(0, "", "", "", "", GetDateInMillis(), "",
                mapOf("" to ""), "summer"),
    //TODO confirm it's the list that has to be null and not the task
    val tasks: List<Tasks>? = emptyList(),
    val isLoading: Boolean = false,
    val isEdit: Boolean = false,
    val userErrorMessage: Int? = null
)
@HiltViewModel
class PlantCreationEditViewModel @Inject constructor (
    private val plantsRepository: PlantsRepository,
    private val tasksRepository: TasksRepository,
    //savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(PlantCreationEditUiState(isLoading = true))
    val uiState: StateFlow<PlantCreationEditUiState> = _uiState.asStateFlow()

//    init {
//        changeQuery(GetDateInMillis())
//    }

    fun getPlantWithId(plantId: Long) {
        // Ui state is refreshing
        _uiState.update { it.copy(isLoading = true) }

        if (plantId.toInt() != -1) {
            viewModelScope.launch(Dispatchers.IO) {
                plantsRepository.getPlantsFromId(plantId).collect { map ->
                    _uiState.update {
                        it.copy(
                            plant = map!!.keys.first()!!,
                            tasks = map.values.first(),
                            isLoading = false
                        )
                    }
                }
            }
       // } else {
       //     _uiState.update {
       //         it.copy(plant = mapOf(Plants(0, "", "", "", "", GetDateInMillis(), "",
       //             mapOf("" to ""), "summer") to emptyList<Tasks>()
       //         ),
       //         isLoading = true) }
        }
    }

    fun createPlant(plant : Plants){
        viewModelScope.launch {
            plantsRepository.addPlants(plant)
        }
    }

    fun deletePlant(plant : Plants){
        viewModelScope.launch {
            plantsRepository.deletePlants(plant)
        }
    }

    fun isValid(
        plant : Plants
    ) : Boolean{
        if (plant.name.isNotEmpty()) {
            return true
        } else {
            // snackbarHostState.showSnackbar("hey")
            return false
        }
    }
}


