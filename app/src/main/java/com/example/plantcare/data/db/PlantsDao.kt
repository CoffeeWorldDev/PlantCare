package com.example.plantcare.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface PlantsDao {
    @Insert
    suspend fun insertPlant(plant: Plants)

    @Delete
    fun deletePlant(plant: Plants)

    @Query("SELECT * FROM plants")
    fun getPlants() : Flow<Array<Plants>>

    @Query(
        """
        SELECT * FROM plants 
        LEFT JOIN tasks 
        ON plants.Plant_id = tasks.Task_owner_plant_id
        """
    )
    fun getPlantsAndTasks(): Flow<Map<Plants?, List<Tasks>>?>

    @Query(
        """
        SELECT * FROM plants 
        JOIN tasks
        ON plants.Plant_id = tasks.Task_owner_plant_id 
        WHERE tasks.Task_is_active = 1
        """
    )
    fun getActivePlants(): Flow<Map<Plants?, List<Tasks>>?>

    @Query(
        """
        SELECT * FROM plants 
        JOIN tasks 
        ON plants.Plant_id = tasks.Task_owner_plant_id 
        WHERE tasks.Task_next_cycle_date = :date
        """
    )
    fun getFutureActivePlants(date: Date): Flow<Map<Plants?, List<Tasks>>?>

    @Query(
        """
        SELECT * FROM plants 
        LEFT JOIN tasks 
        ON plants.Plant_id = tasks.Task_owner_plant_id
        WHERE plants.Plant_id = :plantId
        """
    )
    fun getPlantsFromId(plantId : Long):Flow<Map<Plants?, List<Tasks>>?>
}
