package com.example.plantcare.data.repository

import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.data.repository.dataSource.PlantsLocalDataSource
import com.example.plantcare.domain.repository.PlantsRepository
import com.example.plantcare.ui.utils.GetDateInMillis
import kotlinx.coroutines.flow.Flow
import java.util.Date

class PlantsRepositoryImpl (private val plantsLocalDataSource : PlantsLocalDataSource) : PlantsRepository {
    override suspend fun addPlants(plants: Plants): Long {
        return plantsLocalDataSource.savePlantToDb(plants)
    }

    override suspend fun deletePlants(plants: Plants) {
        plantsLocalDataSource.deletePlant(plants)
    }

    override fun getPlants(): Flow<Array<Plants>> {
        return plantsLocalDataSource.getPlants()
    }

    override fun getActivePlants(date: Date): Flow<Map<Plants?, List<Tasks>>?> {
        return if (date != GetDateInMillis()){
            plantsLocalDataSource.getFutureActivePlants(date)
        } else {
            plantsLocalDataSource.getActivePlants()
        }
    }

    override suspend fun updatePlants(plants: Plants) {
        plantsLocalDataSource.updatePlant(plants)
    }

    override fun getPlantsAndTasks(): Flow<Map<Plants?, List<Tasks>>?> {
        return plantsLocalDataSource.getPlantsAndTasks()
    }

    override fun getFutureActivePlants(date: Date): Flow<Map<Plants?, List<Tasks>>?> {

        return plantsLocalDataSource.getFutureActivePlants(date)
    }

    override fun getPlantsFromId(plantId: Long): Flow<Map<Plants?, List<Tasks>>?> {
        return plantsLocalDataSource.getPlantsFromId(plantId)
    }

}
