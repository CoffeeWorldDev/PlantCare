package com.example.plantcare.ui.di

import com.example.plantcare.data.db.PlantsDao
import com.example.plantcare.data.db.PlantsDatabase
import com.example.plantcare.data.repository.dataSource.PlantsLocalDataSource
import com.example.plantcare.data.repository.dataSourceImpl.PlantsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
 //   @Provides
 //   @Singleton
 //   fun providePlantsRepository(db : PlantsDatabase) : PlantsLocalDataSourceImpl {
 //       return PlantsLocalDataSourceImpl(db.GetPlantsDao())
 //   }
 @Singleton
 @Provides
 fun provideLocalDataSource(plantsDao: PlantsDao):PlantsLocalDataSource{
     return PlantsLocalDataSourceImpl(plantsDao)
 }
}