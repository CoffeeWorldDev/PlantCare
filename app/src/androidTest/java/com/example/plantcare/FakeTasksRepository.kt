package com.example.plantcare

import com.example.plantcare.data.model.Tasks
import com.example.plantcare.domain.repository.TasksRepository
import kotlinx.coroutines.flow.Flow

class FakeTasksRepository : TasksRepository {
    override suspend fun addTasks(task: Tasks) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTasks(task: Tasks) {
        TODO("Not yet implemented")
    }

    override fun getAllTasks(): Flow<List<Tasks>?> {
        TODO("Not yet implemented")
    }

    override fun getTasksFromPlants(plantId: Int): Flow<List<Tasks>?> {
        TODO("Not yet implemented")
    }

    override suspend fun updateTasks(tasks: Tasks) {
        
    }
}