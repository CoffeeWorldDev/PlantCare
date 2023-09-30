package com.example.plantcare.domain.repository

import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    suspend fun addTasks(plants: Plants)
    suspend fun deleteTasks(tasks: Tasks)
    fun getTasks(plants: Plants) : Flow<List<Tasks>>
    suspend fun updateTasks(tasks: Tasks)
}