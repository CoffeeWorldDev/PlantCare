package com.example.plantcare.domain.repository

import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import kotlinx.coroutines.flow.Flow

interface PlantsRepository {
    fun addPlants(plants: Plants)
    fun deletePlants(plants: Plants)
    fun getPlants() : Flow<Map<Plants, List<Tasks>>>
    fun updatePlants(plants: Plants)
}