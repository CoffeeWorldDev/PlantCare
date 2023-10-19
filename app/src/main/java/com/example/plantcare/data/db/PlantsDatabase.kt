package com.example.plantcare.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks

@Database(entities = [Plants::class, Tasks::class],version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PlantsDatabase : RoomDatabase() {
    abstract fun getPlantsDao() : PlantsDao
}