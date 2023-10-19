package com.example.plantcare.ui.di

import com.example.plantcare.domain.repository.PlantsRepository
import com.example.plantcare.domain.repository.TasksRepository
import com.example.plantcare.domain.useCase.plants.AddPlantsUseCase
import com.example.plantcare.domain.useCase.plants.GetPlantsAndTasksUseCase
import com.example.plantcare.domain.useCase.plants.GetActivePlantsUseCase
import com.example.plantcare.domain.useCase.tasks.UpdateTasksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun ProvidesAddPlantsUseCase(
        plantsRepository: PlantsRepository
    ):AddPlantsUseCase{
    return AddPlantsUseCase(plantsRepository)
    }


    @Singleton
    @Provides
    fun ProvidesGetPlantsUseCase(
        plantsRepository: PlantsRepository
    ): GetActivePlantsUseCase {
        return GetActivePlantsUseCase(plantsRepository)
    }

    @Singleton
    @Provides
    fun ProvidesGetPlantsAndTasksUseCase(
        plantsRepository: PlantsRepository
    ): GetPlantsAndTasksUseCase {
        return GetPlantsAndTasksUseCase(plantsRepository)
    }

    @Singleton
    @Provides
    fun ProvidesUpdateTasksUseCase(
        tasksRepository: TasksRepository
    ): UpdateTasksUseCase {
        return UpdateTasksUseCase(tasksRepository)
    }
}