package com.example.plantcare.domain.repository

import com.example.plantcare.data.model.Plants
import kotlinx.coroutines.flow.Flow

interface PlantsRepository {
    suspend fun addPlants(plants: Plants)
    suspend fun deletePlants(plants: Plants)
    fun getPlants() : Flow<List<Plants>>
    suspend fun updatePlants(plants: Plants)
}