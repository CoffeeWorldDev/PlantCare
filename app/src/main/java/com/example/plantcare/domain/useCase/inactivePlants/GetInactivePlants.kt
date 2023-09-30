package com.example.plantcare.domain.useCase.inactivePlants

import com.example.plantcare.data.model.InactivePlants
import com.example.plantcare.domain.repository.InactivePlantsRepository
import kotlinx.coroutines.flow.Flow

class GetInactivePlants(private val inactivePlantsRepository: InactivePlantsRepository) {
    fun execute() : Flow<List<InactivePlants>> {
        return inactivePlantsRepository.getInactivePlants()
    }
}