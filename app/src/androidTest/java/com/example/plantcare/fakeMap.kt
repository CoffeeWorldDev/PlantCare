package com.example.plantcare

import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.ui.util.GetDateInMillis
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.Date

fun fakeMap() : Map<Plants?, List<Tasks>>? {
   var plants = fakePlantsList()
   var tasks = fakeTasksList()
   var fakeMap : Map<Plants?, List<Tasks>>? = mapOf(plants[0] to listOf(tasks[0]), plants[1] to listOf(tasks[1], tasks[2]))
   return fakeMap
}

fun FakeMapDateQuery(): Map<Plants?, List<Tasks>>? {
   var map = fakeMap()
   return emptyMap()

  // map?.filterValues { it.contains(Tasks(3, 2,
  //    "soil", true,
  //    GetDateInMillis(), 11,
  //    4, "urgent",
  //    3, GetDateInMillis(7),"winter"))}
}