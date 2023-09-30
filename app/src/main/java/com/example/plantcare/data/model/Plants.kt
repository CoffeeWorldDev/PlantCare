package com.example.plantcare.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plants")
data class Plants(
    @PrimaryKey(autoGenerate = true)
    var plantsId: Int,
    //@ColumnInfo(name = "Name")
    var name : String,
    //TODO implement type
    //@ColumnInfo(name = "Type")
    var type : String?,
    //@ColumnInfo(name = "Species")
    var species : String?,
    //@ColumnInfo(name = "Position")
    var position : String?,
    //@ColumnInfo(name = "Birthday")
    var age : String?,
    //@ColumnInfo(name = "Photo")
    var photo : String?,
    //@ColumnInfo(name = "Notes")
    var notes : Map<String, String>?,
    //@ColumnInfo(name = "Tasks")
    var tasks: List<Tasks>?
)
