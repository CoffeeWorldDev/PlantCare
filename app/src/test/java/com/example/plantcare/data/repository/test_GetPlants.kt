package com.example.plantcare.data.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.plantcare.data.db.PlantsDao
import com.example.plantcare.data.db.PlantsDatabase
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class test_GetPlants {

    private lateinit var itemDao: PlantsDao
    private lateinit var inventoryDatabase: PlantsDatabase

    @BeforeEach
    fun setUp() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        inventoryDatabase = Room.inMemoryDatabaseBuilder(context, PlantsDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        itemDao = inventoryDatabase.GetPlantsDao()
    }

    private var item1 = Plants(1, "Apples", "cactus",
                                "bunnie ear", "bedroom",
                                "one year", "dfjdls", mapOf("34/43/23" to "dfsklj"),
                                "winter")
    private var item2 = Plants(2, "Pears", "succulent",
                                "nipple", "livingRoom",
                               "6 months", "dfjdlghs", mapOf("69/69/69" to "notesnotenote"),
                                "summer")
    private var task1 = Tasks(1, 1,
                                "water", true,
                                "59/53/23", 11,
                                true, 4, "urgent",
                                3, "winter")

    private suspend fun addOneItemToDb() {
        itemDao.insert(item1)
    }

    private suspend fun addTwoItemsToDb() {
        itemDao.insert(item1)
        itemDao.insert(item2)
    }

    @AfterEach
    fun tearDown() {
        inventoryDatabase.close()
    }
    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsItemIntoDB() = runBlocking {
        addOneItemToDb()
        val allItems = itemDao.getOnlyPlants().first()
        assertEquals(allItems, item1)
    }

    @Test
    fun getPlants() {
    }
}