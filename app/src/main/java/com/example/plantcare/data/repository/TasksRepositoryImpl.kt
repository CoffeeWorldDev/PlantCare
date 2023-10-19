package com.example.plantcare.data.repository

import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.data.repository.dataSource.TasksLocalDataSource
import com.example.plantcare.domain.repository.TasksRepository
import kotlinx.coroutines.flow.Flow

class TasksRepositoryImpl(private val tasksLocalDataSource: TasksLocalDataSource) : TasksRepository {

    override suspend fun addTasks(task: Tasks) {
        tasksLocalDataSource.saveTasksToDb(task)
    }

    override suspend fun deleteTasks(task: Tasks) {
        tasksLocalDataSource.deleteTasks(task)
    }

    override fun getAllTasks(): Flow<List<Tasks>?> {
        return tasksLocalDataSource.getAllTasks()
    }

    override fun getTasksFromPlants(plantId: Int): Flow<List<Tasks>?> {
        return tasksLocalDataSource.getTasksFromPlants(plantId)
    }

    override suspend fun updateTasks(tasks: Tasks) {
        TODO("Not yet implemented")
    }
}