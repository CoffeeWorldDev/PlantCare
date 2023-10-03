package com.example.plantcare.data.repository.dataSource

import com.example.plantcare.data.model.Plants

interface PlantsLocalDataSource {
    suspend fun savePlantToDb(plant: Plants)
}

