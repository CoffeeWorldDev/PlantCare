package com.example.plantcare.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(plant: Plants)

    @Query("SELECT * FROM plants JOIN tasks ON plants.plantsId = tasks.ownerPlantId")
    fun getPlants(): Flow<Map<Plants, List<Tasks>>>

}