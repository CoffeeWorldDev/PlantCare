package com.example.plantcare.ui.plantCreationEdit

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.domain.repository.PlantsRepository
import com.example.plantcare.domain.repository.TasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PlantCreationEditUiState(
    val plantMap: Map<Plants?, List<Tasks>>? = null,
    val isLoading: Boolean = false,
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

        viewModelScope.launch {
            plantsRepository.getPlantsFromId(plantId).collect { map ->
                _uiState.update {
                    it.copy(plantMap = map, isLoading = false)
                }
            }
        }
    }

    fun getPhotoFromCamera(){

    }
}


