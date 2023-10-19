package com.example.plantcare.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plants")
data class Plants(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Plant_id")
    var plantsId: Int,
    @ColumnInfo(name = "Plant_name")
    var name : String,
    //TODO implement type
    @ColumnInfo(name = "Plant_type")
    var type : String?,
    @ColumnInfo(name = "Plant_species")
    var species : String?,
    @ColumnInfo(name = "Plant_position")
    var position : String?,
    @ColumnInfo(name = "Plant_birthday")
    var age : String?,
    @ColumnInfo(name = "Plant_photo")
    var photo : String?,
    @ColumnInfo(name = "Plant_notes")
    var notes : Map<String, String>?,
    //@ColumnInfo(name = "Tasks")
    //var tasks: List<Tasks>?,
    @ColumnInfo(name = "Plant_current_season")
    var currentSeason: String
)
