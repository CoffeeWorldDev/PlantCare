package com.example.plantcare.domain.useCase.tasks

import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.domain.repository.TasksRepository
import kotlinx.coroutines.flow.Flow

class GetTasksUseCase(private val tasksRepository: TasksRepository) {
    suspend fun execute(plants: Plants) : Flow<List<Tasks>> {
        return tasksRepository.getTasks(plants)
    }
}