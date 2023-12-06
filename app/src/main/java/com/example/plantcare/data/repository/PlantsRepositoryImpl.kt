package com.example.plantcare.data.repository

import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.data.repository.dataSource.PlantsLocalDataSource
import com.example.plantcare.domain.repository.PlantsRepository
import com.example.plantcare.ui.utils.GetDateInMillis
import kotlinx.coroutines.flow.Flow
import java.util.Date

class PlantsRepositoryImpl (private val plantsLocalDataSource : PlantsLocalDataSource) : PlantsRepository {
    override suspend fun addPlants(plant: Plants) {
        plantsLocalDataSource.savePlantToDb(plant)
    }

    override fun deletePlants(plant: Plants) {
        plantsLocalDataSource.deletePlant(plant)
    }

    override fun getActivePlants(date: Date): Flow<Map<Plants?, List<Tasks>>?> {
        if (date != GetDateInMillis()){
            return plantsLocalDataSource.getFutureActivePlants(date)
        } else {
            return plantsLocalDataSource.getActivePlants()
        }
    }

    override fun updatePlants(plant: Plants) {
        TODO("Not yet implemented")
    }

    override fun getPlantsAndTasks(): Flow<Map<Plants?, List<Tasks>>?> {
        return plantsLocalDataSource.getPlantsAndTasks()
    }

    override fun getFutureActivePlants(date: Date): Flow<Map<Plants?, List<Tasks>>?> {

        return plantsLocalDataSource.getFutureActivePlants(date)
    }

}
