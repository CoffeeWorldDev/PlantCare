package com.example.plantcare.ui.di

import com.example.plantcare.data.db.PlantsDao
import com.example.plantcare.data.db.TasksDao
import com.example.plantcare.data.repository.dataSource.PlantsLocalDataSource
import com.example.plantcare.data.repository.dataSource.TasksLocalDataSource
import com.example.plantcare.data.repository.dataSourceImpl.PlantsLocalDataSourceImpl
import com.example.plantcare.data.repository.dataSourceImpl.TasksLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

 @Singleton
 @Provides
 fun provideLocalPlantsDataSource(plantsDao: PlantsDao):PlantsLocalDataSource{
     return PlantsLocalDataSourceImpl(plantsDao)
 }

 @Singleton
 @Provides
 fun provideLocalTasksDataSource(tasksDao: TasksDao):TasksLocalDataSource{
  return TasksLocalDataSourceImpl(tasksDao)
 }
}