package com.example.plantcare.ui.plantsGallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.domain.repository.PlantsRepository
import com.example.plantcare.domain.useCase.plants.GetActivePlantsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PlantsGalleryUiState(
    val plantsMap: Map<Plants?, List<Tasks>>? = null,
    var currentLayout: Int = 1,
    val isLoading: Boolean = false,
    val userErrorMessage: Int? = null
)
@HiltViewModel
class PlantsGalleryViewModel @Inject constructor (
    private val plantsRepository: PlantsRepository
    //savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(PlantsGalleryUiState(isLoading = true))
    val uiState: StateFlow<PlantsGalleryUiState> = _uiState.asStateFlow()

    init {
        setUpGallery()
    }


    fun setUpGallery() {
        // Ui state is refreshing
        viewModelScope.launch(Dispatchers.IO) {
            plantsRepository.getPlantsAndTasks().collect { map ->
                _uiState.update {
                    it.copy(plantsMap = map, isLoading = false)
                }
            }
        }
    }

    //TODO implement search
    fun searchPlant(){

    }

    //TODO implement change Layout
    fun sortGallery(layout : Int) {
        _uiState.update {
            it.copy(currentLayout = layout)
        }
    }
}


