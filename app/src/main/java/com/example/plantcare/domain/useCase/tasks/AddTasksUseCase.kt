package com.example.plantcare.domain.useCase.tasks

import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.domain.repository.TasksRepository

class AddTasksUseCase(private val tasksRepository: TasksRepository) {
    suspend fun execute(task: Tasks){
        return tasksRepository.addTasks(task)
    }
}