package com.example.plantcare.data.repository.dataSource

import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import kotlinx.coroutines.flow.Flow

interface PlantsLocalDataSource {
    suspend fun savePlantToDb(plant: Plants)
    fun deletePlant(plant: Plants)
    fun getActivePlants() : Flow<Map<Plants?, List<Tasks>>?>
    fun getPlantsAndTasks() : Flow<Map<Plants?, List<Tasks>>?>
}

