package com.example.plantcare.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.plantcare.data.db.TasksDao
import com.example.plantcare.data.db.TasksDatabase
import com.example.plantcare.fakePlantsList
import com.example.plantcare.fakeTasksList
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException



@RunWith(AndroidJUnit4ClassRunner::class)
class TasksDaoTest {

    private lateinit var tasksDao: TasksDao
    private lateinit var db: TasksDatabase

    private val plantsList = fakePlantsList()
    private val tasksList = fakeTasksList()

    @Before
    fun createDb() = runBlocking(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, TasksDatabase::class.java).build()
        tasksDao = db.GetTasksDao()
    }
    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun dao_AddAndGetAllTasks_success() = runBlocking{
        tasksDao.insertTask(tasksList[0])
        tasksDao.insertTask(tasksList[1])
        var returnAllTasks = tasksDao.getAllTasks().first()
        Assert.assertNotNull(returnAllTasks)
        Assert.assertEquals(tasksList[0].name, returnAllTasks!![0].name)
        Assert.assertEquals(tasksList[1].taskId, returnAllTasks!![1].taskId)
    }

    @Test
    @Throws(Exception::class)
    fun dao_AddAndDeleteTasksFromPlants_success() = runBlocking{
        tasksDao.insertTask(tasksList[0])
        tasksDao.insertTask(tasksList[1])
        tasksDao.insertTask(tasksList[2])
        var returnTasksFromPlants = tasksDao.getTasksFromPlants(plantsList[1].plantsId).first()
        Assert.assertEquals(2, returnTasksFromPlants!!.size)
        var returnAllTasks = tasksDao.getAllTasks().first()
        tasksDao.deleteTask(returnAllTasks!![1])
        tasksDao.deleteTask(returnAllTasks!![2])
        var returnAfterDeletePlants = tasksDao.getTasksFromPlants(plantsList[1].plantsId).first()
        Assert.assertEquals(0, returnAfterDeletePlants!!.size)
    }

    @Test
    @Throws(Exception::class)
    fun dao_EditTasks_success() = runBlocking{
        tasksDao.insertTask(tasksList[0])
        tasksDao.insertTask(tasksList[1])
        tasksDao.insertTask(tasksList[2])
        var returnTasksFromPlants = tasksDao.getAllTasks().first()
        val plantToBeChanged = returnTasksFromPlants!![0]
        plantToBeChanged.daysUntilNextCycle = plantToBeChanged.cycleLength
        tasksDao.updateTask(plantToBeChanged)
        returnTasksFromPlants = tasksDao.getAllTasks().first()
        Assert.assertEquals(11, returnTasksFromPlants!![0].daysUntilNextCycle)
    }
}