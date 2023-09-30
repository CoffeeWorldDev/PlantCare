package com.example.plantcare.domain.useCase.inactivePlants

import com.example.plantcare.data.model.InactivePlants
import com.example.plantcare.domain.repository.InactivePlantsRepository

class DeleteInactivePlants(private val inactivePlantsRepository: InactivePlantsRepository) {
    suspend fun execute(inactivePlants: InactivePlants){
        return inactivePlantsRepository.deleteInactivePlants(inactivePlants)
    }
}