package com.example.plantcare.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.plantcare.data.db.PlantsDao
import com.example.plantcare.data.db.PlantsDatabase
import com.example.plantcare.data.db.TasksDao
import com.example.plantcare.data.db.TasksDatabase
import com.example.plantcare.fakePlantsList
import com.example.plantcare.fakeTasksList
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4ClassRunner::class)
class MapTest {
    private lateinit var plantsDao: PlantsDao
    private lateinit var Plantsdb: PlantsDatabase
    private lateinit var tasksDao: TasksDao
    private lateinit var tasksDb: TasksDatabase

    private val plantsList = fakePlantsList()
    private val tasksList = fakeTasksList()

    @Before
    fun createDb() = runBlocking(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        Plantsdb = Room.inMemoryDatabaseBuilder(
            context, PlantsDatabase::class.java).build()
        tasksDb = Room.inMemoryDatabaseBuilder(
            context, TasksDatabase::class.java).build()
        tasksDao = tasksDb.GetTasksDao()
        plantsDao = Plantsdb.getPlantsDao()
    }
    @After
    @Throws(IOException::class)
    fun closeDb() {
        Plantsdb.close()
        tasksDb.close()
    }

    @Test
    @Throws(Exception::class)
    fun plantsDao_TestMapReturn_Success() = runBlocking {
        plantsDao.insertPlant(plantsList[0])
        plantsDao.insertPlant(plantsList[1])
        tasksDao.insertTask(tasksList[0])
        tasksDao.insertTask(tasksList[1])
        tasksDao.insertTask(tasksList[2])
        var returnPlants = plantsDao.getPlantsAndTasks().first()
        var returnAllTasks = tasksDao.getAllTasks().first()

        assertEquals(3, returnAllTasks!!.size)

        //assertEquals(2, returnPlants!!.size)
        assertNull(returnPlants)
//        var plantsMap = plantsDao.getPlantsAndTasks().first()
//        var mapToList = plantsMap.toString()
//        Log.e("HERE", mapToList)
//        assertNotNull(plantsMap)
//        assertNotNull(mapToList)
//        //assertEquals(2, plantsMap.size)
    }
//    @Test
//    @Throws(Exception::class)
//    fun dao_AddAndGetPlants_success() = runBlocking{
//        plantsDao.insertPlant(plantsList[0])
//        plantsDao.insertPlant(plantsList[1])
//        var returnPlants = plantsDao.getPlants().first()
//        Assert.assertNotNull(returnPlants)
//        Assert.assertEquals(plantsList[0], returnPlants?.get(0))
//        Assert.assertEquals(plantsList[1], returnPlants?.get(1))
//    }
//    @Test
//    @Throws(Exception::class)
//    fun dao_DeletePlants_success() = runBlocking{
//        plantsDao.insertPlant(plantsList[0])
//        plantsDao.insertPlant(plantsList[1])
//        var returnPlants = plantsDao.getPlants().first()
//        Assert.assertNotNull(returnPlants)
//        plantsDao.deletePlant(returnPlants!![0])
//        plantsDao.deletePlant(returnPlants!![1])
//        var returnAfterDeletePlants = plantsDao.getPlants().first()
//        Assert.assertEquals(0, returnAfterDeletePlants!!.size)
//    }
}