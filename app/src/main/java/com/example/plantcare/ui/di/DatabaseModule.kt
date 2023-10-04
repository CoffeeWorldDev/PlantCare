package com.example.plantcare.ui.di

import android.app.Application
import androidx.room.Room
import com.example.plantcare.data.db.PlantsDao
import com.example.plantcare.data.db.PlantsDatabase
import com.example.plantcare.data.repository.dataSourceImpl.PlantsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providePlantsDatabase(app: Application) : PlantsDatabase {
        return Room.databaseBuilder(
            app,
            PlantsDatabase::class.java,
            "plants_database"
        ).build()
    }
 //   @Provides
 //   @Singleton
 //   fun provideTasksDatabase(app: Application) :  {
 //       return Room.databaseBuilder(
 //           app,
 //           PlantsDatabase::class.java,
 //           "plants_database"
 //       ).build()
 //   }

    @Singleton
    @Provides
    fun providePlantsDao(plantsDatabase: PlantsDatabase): PlantsDao {
        return plantsDatabase.GetPlantsDao()
    }

}