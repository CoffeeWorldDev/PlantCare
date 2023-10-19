package com.example.plantcare.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
class Tasks (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Task_id")
    var taskId: Int,
    @ColumnInfo(name = "Task_owner_plant_id")
    var ownerPlantId : Int,
    @ColumnInfo(name = "Task_name")
    var name : String,
    @ColumnInfo(name = "Task_is_active")
    var isActive : Boolean,
    @ColumnInfo(name = "Task_last_completed")
    var lastCompleted : String,
    @ColumnInfo(name = "Task_cycle_length")
    var cycleLength : Int,
    @ColumnInfo(name = "Task_days_uncompleted")
    var daysUncompleted : Int,
    @ColumnInfo(name = "Task_urgency")
    var urgency : String,
    @ColumnInfo(name = "Task_days_until_next_cycle")
    var daysUntilNextCycle : Int,
    @ColumnInfo(name = "Task_current_season")
    var currentSeason: String
    )