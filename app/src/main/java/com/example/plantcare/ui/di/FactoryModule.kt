package com.example.plantcare.ui.di

import com.example.plantcare.domain.useCase.plants.AddPlantsUseCase
import com.example.plantcare.domain.useCase.plants.GetPlantsAndTasksUseCase
import com.example.plantcare.domain.useCase.plants.GetActivePlantsUseCase
import com.example.plantcare.domain.useCase.tasks.UpdateTasksUseCase
import com.example.plantcare.ui.home.HomeViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
fun ProvidesHomeFactoryModel(
        getActivePlantsUseCase: GetActivePlantsUseCase,
        updateTasksUseCase: UpdateTasksUseCase
): HomeViewModelFactory {
    return HomeViewModelFactory(
        getActivePlantsUseCase,
        updateTasksUseCase
    )
}
}