package com.example.plantcare.domain.useCase.plants

import com.example.plantcare.data.model.Plants
import com.example.plantcare.domain.repository.PlantsRepository

class AddPlantsUseCase(private val plantsRepository: PlantsRepository) {
    suspend fun execute(plants: Plants): Long {
        return plantsRepository.addPlants(plants)
    }
}