package com.example.plantcare.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
class Tasks (
    @PrimaryKey(autoGenerate = true)
    var taskId: Int,
    //@ColumnInfo(name = "OwnerPlantId")
    var ownerPlantId : Int,
    //@ColumnInfo(name = "Name")
    var name : String,
    //@ColumnInfo(name = "IsActive")
    var isActive : Boolean,
    //@ColumnInfo(name = "LastCompleted")
    var lastCompleted : String,
    //@ColumnInfo(name = "CycleLength")
    var cycleLength : Int,
    //@ColumnInfo(name = "IsToBeCompleted")
    var isToBeCompleted : Boolean,
    //@ColumnInfo(name = "DaysUncompleted")
    var daysUncompleted : Int,
    //@ColumnInfo(name = "Urgency")
    var urgency : String,
    //@ColumnInfo(name = "DaysUntilNextCycle")
    var daysUntilNextCycle : Int,
    var season: String
    )