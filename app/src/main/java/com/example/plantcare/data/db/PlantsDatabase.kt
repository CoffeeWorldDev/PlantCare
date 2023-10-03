package com.example.plantcare.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.plantcare.data.model.Plants

@Database(entities = [Plants::class],version = 1, exportSchema = false)
abstract class PlantsDatabase : RoomDatabase() {
    abstract val plantsDao : PlantsDao
}