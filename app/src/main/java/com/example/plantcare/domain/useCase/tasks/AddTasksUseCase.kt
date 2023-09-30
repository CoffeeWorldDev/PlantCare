package com.example.plantcare.domain.useCase.tasks

import com.example.plantcare.data.model.Plants
import com.example.plantcare.domain.repository.TasksRepository

class AddTasksUseCase(private val tasksRepository: TasksRepository) {
    suspend fun execute(plants: Plants){
        return tasksRepository.addTasks(plants)
    }
}