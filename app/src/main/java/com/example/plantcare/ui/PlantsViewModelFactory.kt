package com.example.plantcare.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.plantcare.domain.useCase.plants.AddPlantsUseCase
import com.example.plantcare.domain.useCase.plants.GetPlantsUseCase

class PlantsViewModelFactory(
    private val addPlantsUseCase: AddPlantsUseCase,
    private val getPlantsUseCase: GetPlantsUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlantsViewModel(
            addPlantsUseCase,
            getPlantsUseCase
        ) as T
    }
}