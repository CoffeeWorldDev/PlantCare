package com.example.plantcare.ui.di

import com.example.plantcare.domain.useCase.plants.AddPlantsUseCase
import com.example.plantcare.domain.useCase.plants.GetPlantsUseCase
import com.example.plantcare.ui.PlantsViewModelFactory
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
fun ProvidesPlantsFactoryModel(
        addPlantsUseCase: AddPlantsUseCase,
        getPlantsUseCase: GetPlantsUseCase
): PlantsViewModelFactory {
    return PlantsViewModelFactory(
        addPlantsUseCase,
        getPlantsUseCase
    )
}
}