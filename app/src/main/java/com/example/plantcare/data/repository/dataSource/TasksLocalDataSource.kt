package com.example.plantcare.data.repository.dataSource

import com.example.plantcare.data.model.Tasks
import kotlinx.coroutines.flow.Flow

interface TasksLocalDataSource {
    suspend fun saveTasksToDb(tasks: Tasks)
    fun deleteTasks(tasks: Tasks)
    fun getAllTasks() : Flow<List<Tasks>?>
    fun getTasksFromPlants(plantId : Int) : Flow<List<Tasks>?>
}