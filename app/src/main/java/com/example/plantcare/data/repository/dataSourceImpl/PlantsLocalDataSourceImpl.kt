package com.example.plantcare.data.repository.dataSourceImpl

import com.example.plantcare.data.db.PlantsDao
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.data.repository.dataSource.PlantsLocalDataSource
import kotlinx.coroutines.flow.Flow

class PlantsLocalDataSourceImpl(private val plantsDao : PlantsDao) : PlantsLocalDataSource {
    override suspend fun savePlantToDb(plant: Plants) {
        plantsDao.insertPlant(plant)
    }

    override fun deletePlant(plant: Plants) {
        plantsDao.deletePlant(plant)
    }

    override fun getActivePlants(): Flow<Map<Plants?, List<Tasks>>?> {
       return plantsDao.getActivePlants()
    }

    override fun getPlantsAndTasks(): Flow<Map<Plants?, List<Tasks>>?> {
        return plantsDao.getPlantsAndTasks()
    }
}