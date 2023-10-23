package com.example.plantcare

import com.example.plantcare.data.model.Tasks

fun fakeTasksList() : ArrayList<Tasks> {

    var task1 = Tasks(1, 1,
        "water", true,
        "59/53/23", 11,
         4, "urgent",
        3, "winter")
    var task2 = Tasks(2, 2,
        "soil", true,
        "59/53/23", 11,
         4, "urgent",
        3, "winter")
    var task3 = Tasks(3, 2,
        "soil", true,
        "59/53/23", 11,
         4, "urgent",
        3, "winter")

    return arrayListOf<Tasks>(task1, task2, task3)
}