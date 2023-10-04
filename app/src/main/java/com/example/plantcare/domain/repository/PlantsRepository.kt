package com.example.plantcare.domain.repository

import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import kotlinx.coroutines.flow.Flow

interface PlantsRepository {
    suspend fun addPlants(plants: Plants)
    suspend fun deletePlants(plants: Plants)
    suspend fun getPlants() : Flow<Map<Plants, List<Tasks>>>
    suspend fun updatePlants(plants: Plants)
}