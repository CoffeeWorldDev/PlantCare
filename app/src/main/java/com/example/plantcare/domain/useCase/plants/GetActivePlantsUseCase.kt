package com.example.plantcare.domain.useCase.plants

import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.domain.repository.PlantsRepository
import com.example.plantcare.ui.utils.GetDateInMillis
import kotlinx.coroutines.flow.Flow

class GetActivePlantsUseCase(private val plantsRepository: PlantsRepository) {
    fun execute(): Flow<Array<Plants>> {
        return plantsRepository.getPlants()
       // return plantsRepository.getActivePlants(GetDateInMillis())
    }
}