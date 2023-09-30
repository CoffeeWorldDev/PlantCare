package com.example.plantcare.domain.useCase.plants

import com.example.plantcare.data.model.Plants
import com.example.plantcare.domain.repository.PlantsRepository
import kotlinx.coroutines.flow.Flow

class AddPlantsUseCase(private val plantsRepository: PlantsRepository) {
    suspend fun execute(plants: Plants){
        return plantsRepository.addPlants(plants)
    }
}