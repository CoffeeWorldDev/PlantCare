package com.example.plantcare.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.plantcare.data.model.Tasks
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Insert
    suspend fun insertTask(task: Tasks)

    @Delete
    suspend fun deleteTask(task: Tasks)

    @Update
    suspend fun updateTask(task: Tasks)

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<Tasks>?>

    @Query("SELECT * FROM tasks WHERE tasks.Task_owner_plant_id LIKE :plantId")
    fun getTasksFromPlants(plantId: Long): Flow<List<Tasks>?>
}