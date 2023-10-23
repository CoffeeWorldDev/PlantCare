package com.example.plantcare.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.plantcare.domain.useCase.plants.AddPlantsUseCase
import com.example.plantcare.domain.useCase.plants.GetPlantsAndTasksUseCase
import com.example.plantcare.domain.useCase.plants.GetActivePlantsUseCase
import com.example.plantcare.domain.useCase.plants.GetFutureActivePlantsUseCase
import com.example.plantcare.domain.useCase.tasks.UpdateTasksUseCase

class HomeViewModelFactory(private val getActivePlantsUseCase: GetActivePlantsUseCase,
                           private val updateTasksUseCase: UpdateTasksUseCase,
                           private val getFutureActivePlantsUseCase: GetFutureActivePlantsUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(
            getActivePlantsUseCase,
            updateTasksUseCase,
            getFutureActivePlantsUseCase
        ) as T
    }
}