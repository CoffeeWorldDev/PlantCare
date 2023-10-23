package com.example.plantcare.ui

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.plantcare.data.db.PlantsDatabase
import com.example.plantcare.data.db.TasksDao
import com.example.plantcare.data.db.TasksDatabase
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.domain.useCase.plants.GetActivePlantsUseCase
import com.example.plantcare.domain.useCase.plants.GetFutureActivePlantsUseCase
import com.example.plantcare.domain.useCase.tasks.UpdateTasksUseCase
import com.example.plantcare.fakeTasksList
import com.example.plantcare.ui.home.HomeViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4ClassRunner::class)
@HiltAndroidTest
class homeViewModelTest {

    private lateinit var plantsdatabase: PlantsDatabase
    private lateinit var tasksDatabase: TasksDatabase
    private lateinit var tasksDao: TasksDao
    private lateinit var viewModel: HomeViewModel
    private val hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()

    val tasksList = fakeTasksList()

    @get:Rule
    val rule: RuleChain = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)

    @Inject
    lateinit var getActivePlantsUseCase: GetActivePlantsUseCase

    @Inject
    lateinit var updateTasksUseCase: UpdateTasksUseCase

    @Inject
    lateinit var getFutureActivePlantsUseCase: GetFutureActivePlantsUseCase

    @Before
    fun setUp() {
        hiltRule.inject()
        val context = ApplicationProvider.getApplicationContext<Context>()
        plantsdatabase = Room.inMemoryDatabaseBuilder(
            context, PlantsDatabase::class.java).build()
        tasksDatabase = Room.inMemoryDatabaseBuilder(
            context, TasksDatabase::class.java).build()
        tasksDao = tasksDatabase.GetTasksDao()
        viewModel = HomeViewModel(getActivePlantsUseCase, updateTasksUseCase, getFutureActivePlantsUseCase)
    }

    @After
    fun tearDown() {
        plantsdatabase.close()
    }

    fun setUpTask() : List<Tasks>? = runBlocking{
        tasksDao.insertTask(tasksList[0])
        tasksDao.insertTask(tasksList[1])
        tasksDao.insertTask(tasksList[2])
        tasksDao.getAllTasks().first()
    }

    //TODO test more once return map is clarified
    @Test
    @Throws(Exception::class)
    fun plantsViewModel_EditTask_success(): Unit = runBlocking{
        tasksDao.insertTask(tasksList[0])
        tasksDao.insertTask(tasksList[1])
        tasksDao.insertTask(tasksList[2])
        var returnTasksFromPlants = tasksDao.getAllTasks().first()
        val plantToBeChanged = returnTasksFromPlants!![0]
        assertEquals(4, plantToBeChanged.daysUncompleted)
        plantToBeChanged.daysUncompleted = 0
        viewModel.updateTask(plantToBeChanged)
        //returnTasksFromPlants = tasksDao.getAllTasks().first()
      //  assertEquals(0, returnTasksFromPlants!![0].daysUncompleted)
      //  plantToBeChanged.daysUntilNextCycle = plantToBeChanged.cycleLength
      //  viewModel.updateTask(plantToBeChanged)
      //  assertEquals(plantToBeChanged.cycleLength, plantToBeChanged.daysUntilNextCycle)
    }

    @Test
    fun plantsViewModel_CallPlantState_NotNull() = runBlocking {
               var currentUiState = viewModel.plantsState.value
               val currentValue = currentUiState.plantsMap
             //  runBlocking {
             //  }
               assertNotNull(currentValue)
    }
}

