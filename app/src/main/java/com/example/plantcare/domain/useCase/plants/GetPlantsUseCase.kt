package com.example.plantcare.domain.useCase.plants

import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.domain.repository.PlantsRepository
import kotlinx.coroutines.flow.Flow

class GetPlantsUseCase(private val plantsRepository: PlantsRepository) {
    suspend fun execute(): Flow<Map<Plants, List<Tasks>>> {
        return plantsRepository.getPlants()
    }
}