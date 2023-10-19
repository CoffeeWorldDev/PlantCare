package com.example.plantcare.domain.repository

import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    suspend fun addTasks(task: Tasks)
    suspend fun deleteTasks(task: Tasks)
    fun getAllTasks() : Flow<List<Tasks>?>
    fun getTasksFromPlants(plantId: Int) : Flow<List<Tasks>?>
    suspend fun updateTasks(tasks: Tasks)
}