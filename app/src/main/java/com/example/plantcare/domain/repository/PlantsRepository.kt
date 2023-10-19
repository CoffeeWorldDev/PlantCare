package com.example.plantcare.domain.repository

import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import kotlinx.coroutines.flow.Flow

interface PlantsRepository {
    suspend fun addPlants(plants: Plants)
    fun deletePlants(plants: Plants)
    fun getActivePlants() : Flow<Map<Plants?, List<Tasks>>?>
    fun updatePlants(plants: Plants)
    fun getPlantsAndTasks() : Flow<Map<Plants?, List<Tasks>>?>
}