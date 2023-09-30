package com.example.plantcare.domain.useCase.tasks

import com.example.plantcare.data.model.Tasks
import com.example.plantcare.domain.repository.TasksRepository

class DeleteTasksUseCase(private val tasksRepository: TasksRepository) {
    suspend fun execute(tasks: Tasks){
        return tasksRepository.deleteTasks(tasks)
    }
}