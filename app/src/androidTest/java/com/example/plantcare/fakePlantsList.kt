package com.example.plantcare

import com.example.plantcare.data.model.Plants
import com.example.plantcare.ui.utils.getDateInMillis

fun fakePlantsList() : Array<Plants> {
    var item1 = Plants(1, "Apples", "cactus",
        "bunnie ear", "bedroom",
        getDateInMillis(), "dfjdls", mapOf("34/43/23" to "dfsklj"),
        "winter")
    var item2 = Plants(2, "Pears", "succulent",
        "nipple", "livingRoom",
        getDateInMillis(), "dfjdlghs", mapOf("69/69/69" to "notesnotenote"),
        "summer")

        return arrayOf<Plants>(item1, item2)
}