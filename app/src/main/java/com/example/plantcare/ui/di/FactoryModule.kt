package com.example.plantcare.ui.di

import com.example.plantcare.domain.repository.PlantsRepository
import com.example.plantcare.domain.repository.TasksRepository
import com.example.plantcare.ui.home.HomeViewModelFactory
import com.example.plantcare.ui.plantsGallery.PlantsGalleryViewModelFactory
import com.example.plantcare.worker.Initializer
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
fun providesHomeFactoryModel(
        plantsRepository: PlantsRepository,
        tasksRepository: TasksRepository,
        workerInitializer: Initializer
): HomeViewModelFactory {
    return HomeViewModelFactory(
        plantsRepository,
        tasksRepository,
        workerInitializer
    )
}

    @Singleton
    @Provides
    fun providesPlantsGalleryFactoryModel(
        plantsRepository: PlantsRepository 
    ): PlantsGalleryViewModelFactory {
        return PlantsGalleryViewModelFactory(
            plantsRepository
        )
    }
}
