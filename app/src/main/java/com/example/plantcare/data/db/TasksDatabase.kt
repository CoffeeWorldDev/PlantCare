package com.example.plantcare.data.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks

@Database(entities = [Tasks::class],
            version = 1, exportSchema = false)
@TypeConverters(DateConverters::class)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun GetTasksDao() : TasksDao
}