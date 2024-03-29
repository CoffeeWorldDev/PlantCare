package com.example.plantcare.data.repository.dataSourceImpl

import com.example.plantcare.data.db.TasksDao
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.data.repository.dataSource.TasksLocalDataSource
import kotlinx.coroutines.flow.Flow

class TasksLocalDataSourceImpl(private val tasksDao : TasksDao) : TasksLocalDataSource {
    override suspend fun saveTasksToDb(tasks: Tasks) {
        tasksDao.insertTask(tasks)
    }

    override suspend fun deleteTasks(tasks: Tasks) {
        tasksDao.deleteTask(tasks)
    }

    override fun getAllTasks(): Flow<List<Tasks>?> {
        return tasksDao.getAllTasks()
    }

    override fun getTasksFromPlants(plantId: Int): Flow<List<Tasks>?> {
        TODO("Not yet implemented")
    }


    override suspend fun updateTask(tasks: Tasks) {
        tasksDao.updateTask(tasks)
    }
}