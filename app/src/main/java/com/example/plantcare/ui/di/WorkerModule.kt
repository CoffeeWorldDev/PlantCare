package com.example.plantcare.ui.di

import android.content.Context
import com.example.plantcare.worker.Initializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WorkerModule {
//    @Singleton
//    @Provides
//    fun providesPlantCreationEditFactoryModel(
//        plantsRepository: PlantsRepository,
//        tasksRepository: TasksRepository
//    ): PlantCreationEditViewModelFactory {
//        return PlantCreationEditViewModelFactory(
//            plantsRepository,
//            tasksRepository
//        )
//    }

    @Singleton
    @Provides
    fun provideWorkerStarter(@ApplicationContext context: Context) = Initializer(context)
}