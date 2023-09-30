package com.example.plantcare.domain.repository

import com.example.plantcare.data.model.InactivePlants
import com.example.plantcare.data.model.Plants
import kotlinx.coroutines.flow.Flow


interface InactivePlantsRepository {
    suspend fun addInactivePlants(plants: Plants)
    suspend fun deleteInactivePlants(inactivePlants: InactivePlants)
    fun getInactivePlants() : Flow<List<InactivePlants>>
}