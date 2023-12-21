package com.example.plantcare

import com.example.plantcare.data.model.Plants
import com.example.plantcare.ui.utils.GetDateInMillis

fun fakePlantsList() : MutableList<Plants> {
    var item1 = Plants(1, "Apples", "cactus",
        "bunnie ear", "bedroom",
        GetDateInMillis(), "dfjdls", mapOf("34/43/23" to "dfsklj"),
        "winter")
    var item2 = Plants(2, "Pears", "succulent",
        "nipple", "livingRoom",
        GetDateInMillis(), "dfjdlghs", mapOf("69/69/69" to "notesnotenote"),
        "summer")

        return mutableListOf<Plants>(item1, item2)
}