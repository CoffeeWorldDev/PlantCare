package com.example.plantcare

import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.domain.repository.PlantsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date

class FakePlantsRepository : PlantsRepository {

 //   private var shouldThrowError = false
//
 //   private val _savedTasks = MutableStateFlow(
 //       Map<Plants?, List<Tasks>>?()
 //   )
 //   val savedTasks: StateFlow<Map<Plants?, List<Tasks>>?> = _savedTasks.asStateFlow()
////
 //   private val observableTasks: Map<Plants?, List<Tasks>>? = savedTasks.map {
 //  //    if (shouldThrowError) {
 //  //        throw Exception("Test exception")
 //  //    } else {
 //  //        it.values.toList()
 //  //    }
 //   }


    override suspend fun addPlants(plants: Plants): Long {
        TODO("Not yet implemented")
    }

    override suspend fun deletePlants(plants: Plants) {
        TODO("Not yet implemented")
    }

    override fun getPlants(): Flow<Array<Plants>> = flow {
        emit(fakePlantsList())
    }

    override fun getActivePlants(date: Date): Flow<Map<Plants?, List<Tasks>>?> = flow {
        emit(fakeMap())
    }

    override suspend fun updatePlants(plants: Plants) {
        TODO("Not yet implemented")
    }

    override fun getPlantsAndTasks(): Flow<Map<Plants?, List<Tasks>>?> = flow {
        emit(fakeMap())
    }

    override fun getFutureActivePlants(date: Date): Flow<Map<Plants?, List<Tasks>>?> = flow {
        emit(FakeMapDateQuery())
    }

    override fun getPlantsFromId(plantId: Long): Flow<Map<Plants?, List<Tasks>>?> {
        TODO("Not yet implemented")
    }
}