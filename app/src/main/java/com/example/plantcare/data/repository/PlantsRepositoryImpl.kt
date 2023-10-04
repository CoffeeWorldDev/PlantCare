package com.example.plantcare.data.repository

import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.data.repository.dataSource.PlantsLocalDataSource
import com.example.plantcare.domain.repository.PlantsRepository
import kotlinx.coroutines.flow.Flow

class PlantsRepositoryImpl (private val plantsLocalDataSource : PlantsLocalDataSource) : PlantsRepository {
    override suspend fun addPlants(plants: Plants) {
        plantsLocalDataSource.savePlantToDb(plants)
    }

    override suspend fun deletePlants(plants: Plants) {
        TODO("Not yet implemented")
    }

    override suspend fun getPlants(): Flow<Map<Plants, List<Tasks>>> {
        return plantsLocalDataSource.getPlants()
    }

    override suspend fun updatePlants(plants: Plants) {
        TODO("Not yet implemented")
    }

}
