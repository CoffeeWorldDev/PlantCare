package com.example.plantcare.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.plantcare.data.model.Plants

@Dao
interface PlantsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(plant: Plants)
}