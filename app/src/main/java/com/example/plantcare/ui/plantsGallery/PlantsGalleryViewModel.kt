package com.example.plantcare.ui.plantsGallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantcare.data.model.Plants
import com.example.plantcare.domain.repository.PlantsRepository
import com.example.plantcare.ui.utils.getElapsedTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PlantsGalleryUiState(
    val plants: List<Plants> = emptyList(),
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


    //TODO implement search
 //   fun searchPlant(){
//
 //   }

    fun changeLayout(layout : Int) {
        _uiState.update {
            it.copy(currentLayout = layout)
        }
    }

    fun sortGallery(query : String){

        var newList: List<Plants>

        viewModelScope.launch(Dispatchers.IO) {
            plantsRepository.getPlants().collect { plant ->
                newList = when(query){
                    "name" -> plant.toList().sortedWith(compareBy { it.name })
                    "type" -> plant.toList().sortedWith(compareBy { it.type })
                    //todo pos not working
                    "position" -> plant.toList().sortedWith(compareBy { it.position })
                    else -> plant.toList().sortedWith(compareBy { getElapsedTime(it.age) })
                }
                _uiState.update {uiState ->
                    uiState.copy(
                        plants = newList,
                        isLoading = false
                    )
                }
            }
        }
    }
}


