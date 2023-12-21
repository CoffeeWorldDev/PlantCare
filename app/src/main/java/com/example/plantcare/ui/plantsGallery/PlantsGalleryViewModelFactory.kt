package com.example.plantcare.ui.plantsGallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.plantcare.domain.repository.PlantsRepository
import com.example.plantcare.domain.repository.TasksRepository
import com.example.plantcare.domain.useCase.plants.GetActivePlantsUseCase

class PlantsGalleryViewModelFactory(private val plantsRepository: PlantsRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlantsGalleryViewModel(
            plantsRepository
        ) as T
    }
}

//private val getActivePlantsUseCase: GetActivePlantsUseCase,
//private val updateTasksUseCase: UpdateTasksUseCase,
//private val getFutureActivePlantsUseCase: GetFutureActivePlantsUseCase