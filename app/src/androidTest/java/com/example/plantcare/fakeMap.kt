package com.example.plantcare

import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

fun fakeMap() : Map<Plants?, List<Tasks>>? {
   var plants = fakePlantsList()
   var tasks = fakeTasksList()
   var fakeMap : Map<Plants?, List<Tasks>>? = mapOf(plants[0] to listOf(tasks[0]), plants[1] to listOf(tasks[1], tasks[2]))
   return fakeMap
}