package com.example.plantcare.domain.useCase.inactivePlants

import com.example.plantcare.data.model.Plants
import com.example.plantcare.domain.repository.InactivePlantsRepository


class AddInactivePlants(private val inactivePlantsRepository: InactivePlantsRepository) {
    suspend fun execute(plants: Plants){
        return inactivePlantsRepository.addInactivePlants(plants)
    }
}