package com.example.plantcare.data.repository.dataSource

import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import kotlinx.coroutines.flow.Flow

interface PlantsLocalDataSource {
    fun savePlantToDb(plant: Plants)

    fun getPlants() : Flow<Map<Plants, List<Tasks>>>
}

