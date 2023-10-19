package com.example.plantcare.repository.data

import android.content.Context
import androidx.room.Room

import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.plantcare.data.db.PlantsDao
import com.example.plantcare.data.db.PlantsDatabase
import com.example.plantcare.repository.fakePlantsList
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4ClassRunner::class)
class daoTest{

    private lateinit var plantsDao: PlantsDao
    private lateinit var db: PlantsDatabase

    private val plantsList = fakePlantsList()
    private val localPlants = mutableListOf(plantsList[0], plantsList[1]).sortedBy { it.plantsId }

    @Before
    fun createDb() = runBlocking(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, PlantsDatabase::class.java).build()
        plantsDao = db.getPlantsDao()
    }
    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

//    @Test
//    @Throws(Exception::class)
//    fun dao_AddAndGetPlants_success() = runBlocking{
//        plantsDao.insertPlant(plantsList[0])
//        plantsDao.insertPlant(plantsList[1])
//        var returnPlants = plantsDao.getActivePlants().first()
//        assertNotNull(returnPlants)
//        assertEquals(plantsList[0], returnPlants?.get(0))
//        assertEquals(plantsList[1], returnPlants?.get(1))
//    }
//    @Test
//    @Throws(Exception::class)
//    fun dao_DeletePlants_success() = runBlocking{
//        plantsDao.insertPlant(plantsList[0])
//        plantsDao.insertPlant(plantsList[1])
//        var returnPlants = plantsDao.getActivePlants().first()
//        assertNotNull(returnPlants)
//        plantsDao.deletePlant(returnPlants!![0])
//        plantsDao.deletePlant(returnPlants!![1])
//        var returnAfterDeletePlants = plantsDao.getActivePlants().first()
//        assertEquals(0, returnAfterDeletePlants!!.size)
//    }
}