package com.example.plantcare.ui

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.plantcare.data.db.PlantsDao
import com.example.plantcare.data.db.PlantsDatabase
import com.example.plantcare.data.db.TasksDao
import com.example.plantcare.data.db.TasksDatabase
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.domain.repository.PlantsRepository
import com.example.plantcare.domain.useCase.plants.GetActivePlantsUseCase
import com.example.plantcare.domain.useCase.plants.GetFutureActivePlantsUseCase
import com.example.plantcare.domain.useCase.tasks.UpdateTasksUseCase
import com.example.plantcare.fakeMap
import com.example.plantcare.fakePlantsList
import com.example.plantcare.fakeTasksList
import com.example.plantcare.ui.home.HomeViewModel
import com.example.plantcare.ui.util.getDateInMillis
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.Date
import javax.inject.Inject


@RunWith(AndroidJUnit4ClassRunner::class)
@HiltAndroidTest
class homeViewModelTest {
    private lateinit var plantsDatabase: PlantsDatabase
    private lateinit var tasksDatabase: TasksDatabase
    private lateinit var tasksDao: TasksDao
    private lateinit var plantsDao : PlantsDao

  //  @Mock
  //  private lateinit var mockObserver: Observer<HomeViewModel.>
    private lateinit var viewModel: HomeViewModel

    private lateinit var viewState: HomeViewModel.homeUiState

    @Captor
    private lateinit var captor: ArgumentCaptor<HomeViewModel.homeUiState>

    @Mock
    private lateinit var plantsMockedList: List<Pair<Plants?, List<Tasks>>>

   // @Mock
   // private lateinit var getPlants  GetActivePlantsUseCase

    private val hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()

    val tasksList = fakeTasksList()
    val plantsList = fakePlantsList()

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
        MockitoAnnotations.openMocks(this)
        hiltRule.inject()
        val context = ApplicationProvider.getApplicationContext<Context>()
        plantsDatabase = Room.inMemoryDatabaseBuilder(
            context, PlantsDatabase::class.java).build()
        tasksDatabase = Room.inMemoryDatabaseBuilder(
            context, TasksDatabase::class.java).build()
        tasksDao = tasksDatabase.GetTasksDao()
        plantsDao = plantsDatabase.getPlantsDao()
        viewModel = HomeViewModel(getActivePlantsUseCase, updateTasksUseCase, getFutureActivePlantsUseCase)
        viewState = HomeViewModel.homeUiState()
    }

    @After
    fun tearDown() {
        //plantsDatabase.close()
    }
    fun setUpTask() : List<Tasks>? = runBlocking{
        tasksDao.insertTask(tasksList[0])
        tasksDao.insertTask(tasksList[1])
        tasksDao.insertTask(tasksList[2])
        tasksDao.getAllTasks().first()
    }
    private fun setUpPlants() = runBlocking{
        plantsDao.insertPlant(plantsList[0])
        plantsDao.insertPlant(plantsList[1])
    }


    @Test
    fun plantsViewModel_CallPlantState_NotNull() = runBlocking {
        setUpPlants()
        val flow = flow {
            emit(fakeMap())
        }
        assertNotNull(flow)
        assertEquals(flowOf(fakeMap()), flow)
       // Mockito.`when`(viewModel.getPlants()).thenReturn(flow)
        var state = viewModel.query
        //Mockito.`when`(state).thenReturn(flow)

       // state.collect {value -> assertNotNull(value) }
        var currentUiState = viewState.plantsMap
        val currentValue = currentUiState?.toList()
        //assertEquals(0, viewModel.uiState.value)
        //assertNotNull(currentValue)
        // assertEquals(plantsList[0], currentValue!![0].first)
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
}

