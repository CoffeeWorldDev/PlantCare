package com.example.plantcare.data.repository

import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.data.repository.dataSource.PlantsLocalDataSource
import com.example.plantcare.domain.repository.PlantsRepository
import kotlinx.coroutines.flow.Flow

class PlantsRepositoryImpl (private val plantsLocalDataSource : PlantsLocalDataSource) : PlantsRepository {
    override fun addPlants(plants: Plants) {
        plantsLocalDataSource.savePlantToDb(plants)
    }

    override fun deletePlants(plants: Plants) {
        TODO("Not yet implemented")
    }

    override fun getPlants(): Flow<Map<Plants, List<Tasks>>> {
        return plantsLocalDataSource.getPlants()
    }

    override fun updatePlants(plants: Plants) {
        TODO("Not yet implemented")
    }

}
