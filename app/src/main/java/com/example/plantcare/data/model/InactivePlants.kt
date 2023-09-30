package com.example.plantcare.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inactivePlants")
class InactivePlants (
    @PrimaryKey(autoGenerate = true)
    var InactivePlantsId: Int,
    //@ColumnInfo(name = "Name")
    var Name : String,
    //@ColumnInfo(name = "DateTurnedInactive")
    var DateTurnedInactive: String,
    //TODO implement type
    //@ColumnInfo(name = "Type")
    var Type : String?,
    //@ColumnInfo(name = "Species")
    var Species : String?,
    //@ColumnInfo(name = "Birthday")
    var birthday : String?,
    //@ColumnInfo(name = "Photo")
    var Photo : String?,
    //@ColumnInfo(name = "Notes")
    var Notes : String?,
    //@ColumnInfo(name = "Age")
    var Age : String?
)