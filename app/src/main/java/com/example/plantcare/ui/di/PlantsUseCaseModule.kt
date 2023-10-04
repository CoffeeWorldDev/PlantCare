package com.example.plantcare.ui.di

import com.example.plantcare.domain.repository.PlantsRepository
import com.example.plantcare.domain.useCase.plants.AddPlantsUseCase
import com.example.plantcare.domain.useCase.plants.GetPlantsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PlantsUseCaseModule {

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
    ): GetPlantsUseCase {
        return GetPlantsUseCase(plantsRepository)
    }
}