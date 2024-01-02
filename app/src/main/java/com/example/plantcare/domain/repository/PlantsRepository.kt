package com.example.plantcare.domain.repository

import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface PlantsRepository {
    suspend fun addPlants(plants: Plants)
    suspend fun deletePlants(plants: Plants)
    fun getPlants() : Flow<Array<Plants>>
    fun getActivePlants(date: Date) : Flow<Map<Plants?, List<Tasks>>?>
    fun updatePlants(plants: Plants)
    fun getPlantsAndTasks() : Flow<Map<Plants?, List<Tasks>>?>
    fun getFutureActivePlants(date: Date): Flow<Map<Plants?, List<Tasks>>?>
    fun getPlantsFromId(plantId : Long): Flow<Map<Plants?, List<Tasks>>?>
}
