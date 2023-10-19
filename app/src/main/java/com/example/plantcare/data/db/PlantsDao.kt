package com.example.plantcare.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantsDao {
    @Insert
    suspend fun insertPlant(plant: Plants)

    @Delete
    fun deletePlant(plant: Plants)

    @Query("SELECT * FROM plants JOIN tasks ON plants.Plant_id = tasks.Task_owner_plant_id")
    fun getPlantsAndTasks(): Flow<Map<Plants?, List<Tasks>>?>

    @Query("SELECT * FROM plants JOIN tasks ON plants.Plant_id = tasks.Task_owner_plant_id WHERE tasks.Task_is_active = 1")
    fun getActivePlants(): Flow<Map<Plants?, List<Tasks>>?>
}