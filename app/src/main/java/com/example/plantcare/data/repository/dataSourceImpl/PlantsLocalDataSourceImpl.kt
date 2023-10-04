package com.example.plantcare.data.repository.dataSourceImpl

import com.example.plantcare.data.db.PlantsDao
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.data.repository.dataSource.PlantsLocalDataSource
import kotlinx.coroutines.flow.Flow

class PlantsLocalDataSourceImpl(private val plantsDao : PlantsDao) : PlantsLocalDataSource {
    override suspend fun savePlantToDb(plant: Plants) {
        plantsDao.insert(plant)
    }

    override suspend fun getPlants(): Flow<Map<Plants, List<Tasks>>> {
       return plantsDao.getPlants()
    }
}