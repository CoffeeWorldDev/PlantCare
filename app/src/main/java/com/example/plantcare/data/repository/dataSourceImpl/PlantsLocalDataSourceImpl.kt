package com.example.plantcare.data.repository.dataSourceImpl

import com.example.plantcare.data.db.PlantsDao
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.data.repository.dataSource.PlantsLocalDataSource
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class PlantsLocalDataSourceImpl @Inject constructor(private val plantsDao : PlantsDao) : PlantsLocalDataSource {
    override suspend fun savePlantToDb(plant: Plants): Long {
        return plantsDao.insertPlant(plant)
    }

    override suspend fun updatePlant(plant: Plants) {
        plantsDao.updatePlant(plant)
    }

    override suspend fun deletePlant(plant: Plants) {
        plantsDao.deletePlant(plant)
    }

    override fun getPlants(): Flow<Array<Plants>> {
        return plantsDao.getPlants()
    }

    override fun getActivePlants(): Flow<Map<Plants?, List<Tasks>>?> {
       return plantsDao.getActivePlants()
    }

    override fun getPlantsAndTasks(): Flow<Map<Plants?, List<Tasks>>?> {
        return plantsDao.getPlantsAndTasks()
    }

    override fun getFutureActivePlants(date: Date): Flow<Map<Plants?, List<Tasks>>?> {
        return plantsDao.getFutureActivePlants(date)
    }

    override fun getPlantsFromId(plantId: Long): Flow<Map<Plants?, List<Tasks>>?> {
        return plantsDao.getPlantsFromId(plantId)
    }
}