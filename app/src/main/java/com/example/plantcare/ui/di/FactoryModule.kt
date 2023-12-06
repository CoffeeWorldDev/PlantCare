package com.example.plantcare.ui.di

import com.example.plantcare.domain.repository.PlantsRepository
import com.example.plantcare.domain.repository.TasksRepository
import com.example.plantcare.ui.home.HomeViewModelFactory
import com.example.plantcare.ui.plantCreationEdit.PlantCreationEditViewModelFactory
import com.example.plantcare.ui.plantsGallery.PlantsGalleryViewModelFactory
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
        plantsRepository: PlantsRepository,
        tasksRepository: TasksRepository
): HomeViewModelFactory {
    return HomeViewModelFactory(
        plantsRepository,
        tasksRepository
    )
}

    @Singleton
    @Provides
    fun ProvidesPlantsGalleryFactoryModel(
        plantsRepository: PlantsRepository
    ): PlantsGalleryViewModelFactory {
        return PlantsGalleryViewModelFactory(
            plantsRepository
        )
    }


@Singleton
@Provides
fun ProvidesPlantCreationEditFactoryModel(
    plantsRepository: PlantsRepository,
    tasksRepository: TasksRepository
): PlantCreationEditViewModelFactory {
    return PlantCreationEditViewModelFactory(
        plantsRepository,
        tasksRepository
    )
}
}