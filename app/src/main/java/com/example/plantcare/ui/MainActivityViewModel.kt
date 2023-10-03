package com.example.plantcare.ui

import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks

class MainActivityViewModel() {

    //TODO delete and replace
    fun getPlants() = listOf(
        Plants(1,"plant1","succulent","a species","", "69", "", mapOf(),
            listOf( Tasks(0, 0, "task 1", true, "", 0, true, 0, "", 0, ""),
                Tasks(0, 0, "task 2", true, "", 0, true, 0, "", 0, "")
            ), ""   ),
        Plants(2,"plant2which" + "has a long","cactus","another species","", "420", "", mapOf(),
            listOf( Tasks(0, 0, "task 1", true, "", 0, true, 0, "", 0,""),
                Tasks(0, 0, "task 2", true, "", 0, true, 0, "", 0,""),
                Tasks(0, 0, "task 3", true, "", 0, true, 0, "", 0,""),
                Tasks(0, 0, "task 4", true, "", 0, true, 0, "", 0,"")
            ), ""   ),
        Plants(3,"plant3g","succulent","species again","", "453", "", mapOf(),
            listOf( Tasks(0, 0, "task 1", true, "", 0, true, 0, "", 0, ""),
                Tasks(0, 0, "task 2", true, "", 0, true, 0, "", 0, "")
            ),""   ),
        Plants(4,"Nipple","succulent","nipple cactus","", "143", "", mapOf("20/3/23" to "here's a note", "9/29/23" to "Here's a much longer to not to check how this stuff looks in multiple lines and yeah it seems to be working okay", "30/60/40" to "another one to see if it's scrollable"),
            listOf(
                Tasks(0, 0, "task 1", true, "", 0, true, 6, "", 7, "winter"),
                Tasks(0, 0, "task 2", true, "", 0, true, 9, "", 3, "summer"),
                Tasks(0, 0, "task 3", true, "", 0, true, 9, "", 3, "summer")
            ) , "summer"
        ),
        Plants(5,"plant5","cactus","species part 5","", "786", "", mapOf(),
            listOf( Tasks(0, 0, "task 1", true, "", 0, true, 5, "", 3, ""),
                Tasks(0, 0, "task 2", true, "", 0, true, 0, "", 0, "")
            ), ""   ),
    )

}