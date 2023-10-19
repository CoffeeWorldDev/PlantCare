package com.example.plantcare.domain.useCase.tasks

import com.example.plantcare.data.model.Tasks
import com.example.plantcare.domain.repository.TasksRepository
import kotlinx.coroutines.flow.Flow

class GetTasksFromPlantUseCase(private val tasksRepository: TasksRepository) {
    suspend fun execute(plantId:Int) : Flow<List<Tasks>?> {
        return tasksRepository.getTasksFromPlants(plantId)
    }
}