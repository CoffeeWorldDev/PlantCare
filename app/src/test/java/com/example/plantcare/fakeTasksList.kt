package com.example.plantcare

import com.example.plantcare.data.model.Tasks
import com.example.plantcare.ui.utils.getDateInMillis

fun fakeTasksList() : ArrayList<Tasks> {

    var task1 = Tasks(1, 1,
        "water", true,
        getDateInMillis(), 11,
        4, "urgent",
        3, getDateInMillis(20), "winter")
    var task2 = Tasks(2, 2,
        "soil", true,
        getDateInMillis(), 11,
        4, "urgent",
        3, getDateInMillis(7),"winter")
    var task3 = Tasks(3, 2,
        "soil", true,
        getDateInMillis(), 11,
        4, "urgent",
        3, getDateInMillis(7),"winter")

    return arrayListOf<Tasks>(task1, task2, task3)
}