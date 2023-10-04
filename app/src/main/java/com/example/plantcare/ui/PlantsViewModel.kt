package com.example.plantcare.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantcare.data.model.Plants
import com.example.plantcare.domain.useCase.plants.AddPlantsUseCase
import com.example.plantcare.domain.useCase.plants.GetPlantsUseCase
import kotlinx.coroutines.launch

class PlantsViewModel(private val addPlantsUseCase: AddPlantsUseCase,
                      private val getPlantsUseCase: GetPlantsUseCase) : ViewModel() {
    fun addPlant(plant: Plants) = viewModelScope.launch {
        addPlantsUseCase.execute(plant)
    }

    fun getPlants() = viewModelScope.launch {
        getPlantsUseCase.execute()
    }
}