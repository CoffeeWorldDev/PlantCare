package com.example.plantcare.data.repository.dataSource

import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface PlantsLocalDataSource {
    suspend fun savePlantToDb(plant: Plants) : Long
    suspend fun updatePlant(plant: Plants)
    suspend fun deletePlant(plant: Plants)
    fun getPlants() : Flow<Array<Plants>>
    fun getActivePlants() : Flow<Map<Plants?, List<Tasks>>?>
    fun getPlantsAndTasks() : Flow<Map<Plants?, List<Tasks>>?>
    fun getFutureActivePlants(date: Date): Flow<Map<Plants?, List<Tasks>>?>
    fun getPlantsFromId(plantId : Long): Flow<Map<Plants?, List<Tasks>>?>
}

