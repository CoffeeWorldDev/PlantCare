package com.example.plantcare.ui.di

import com.example.plantcare.data.repository.PlantsRepositoryImpl
import com.example.plantcare.data.repository.TasksRepositoryImpl
import com.example.plantcare.data.repository.dataSource.PlantsLocalDataSource
import com.example.plantcare.data.repository.dataSource.TasksLocalDataSource
import com.example.plantcare.domain.repository.PlantsRepository
import com.example.plantcare.domain.repository.TasksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {

    @Singleton
    @Provides
    fun providePlantsRepository(
        plantsLocalDataSource: PlantsLocalDataSource
    ): PlantsRepository {
        return PlantsRepositoryImpl(
            plantsLocalDataSource
        )
    }

    @Singleton
    @Provides
    fun provideTasksRepository(
        tasksLocalDataSource: TasksLocalDataSource
    ): TasksRepository {
        return TasksRepositoryImpl(
            tasksLocalDataSource
        )
    }
}
