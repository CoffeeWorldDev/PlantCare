package com.example.plantcare.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.plantcare.FakePlantsRepository
import com.example.plantcare.FakeTasksRepository
import com.example.plantcare.fakeMap
import com.example.plantcare.fakePlantsList
import com.example.plantcare.fakeTasksList
import com.example.plantcare.ui.home.HomeViewModel
import com.example.plantcare.ui.util.GetDateInMillis
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4ClassRunner::class)
class homeViewModelTest {

    private val hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()

    val tasksList = fakeTasksList()
    val plantsList = fakePlantsList()

    @get:Rule
    val rule: RuleChain = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun homeViewModel_flowTest_success() = runTest {
        val fakePlantsRepository = FakePlantsRepository()
        val fakeTasksRepository = FakeTasksRepository()
        val viewModel = HomeViewModel(fakePlantsRepository, fakeTasksRepository)
        fakePlantsRepository.getActivePlants(GetDateInMillis(70))

       backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect()
        }
        val uiState = viewModel.uiState.first()
        assertNotNull(uiState)
        //advanceTimeBy(500)

        var valRep = fakePlantsRepository.getActivePlants(GetDateInMillis()).first()?.keys
        var vmRep =  viewModel.uiState.value
        //assertNotNull(uiState.plantsMap)
      //  assertEquals(valRep, vmRep)
      //  assertEquals(valRep?.size, vmRep?.size)
      //  assertEquals(2, vmRep?.size)
      //  viewModel.observeMap(GetDateInMillis(7))
      //  valRep = fakePlantsRepository.getFutureActivePlants(GetDateInMillis(7)).first()?.keys
      //  vmRep = viewModel.uiState.value.plantsMap?.keys
      //  assertEquals(0, valRep?.size)
      //  assertEquals(valRep?.size, vmRep?.size)
      //  assertEquals(valRep, vmRep)
    }


    //TODO test if the task gets edited
    @Test
    @Throws(Exception::class)
    fun homeViewModel_EditTask_success(): Unit = runTest{
        val fakePlantsRepository = FakePlantsRepository()
        val fakeTasksRepository = FakeTasksRepository()
        val viewModel = HomeViewModel(fakePlantsRepository, fakeTasksRepository)
        // TODO commented until uiState is back
//        fakePlantsRepository.getActivePlants()
//        // Create an empty collector for the StateFlow
//        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
//            viewModel.uiState.collect()
//        }
//
//        var valRep = fakePlantsRepository.getActivePlants().first()?.values
//        var vmRep =  viewModel.uiState.value.plantsMap?.values
//        // assertEquals(valRep, vmRep)
//
//        var value = viewModel.uiState.value.plantsMap?.values
//        var el = value?.elementAt(0)
//        assertNotNull(el!![0])
//        assertEquals(4, el!![0].daysUncompleted)
//
//
//        viewModel.updateTask(el!![0])
//        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
//            viewModel.uiState.collect()
//        }
//        var value2 = viewModel.uiState.value.plantsMap?.values
//        var el2 = value?.elementAt(0)
//
//        assertEquals(4, el2!![0].daysUncompleted)
     //   tasksDao.insertTask(tasksList[0])
     //   tasksDao.insertTask(tasksList[1])
     //   tasksDao.insertTask(tasksList[2])
        //var returnTasksFromPlants = tasksDao.getAllTasks().first()
        //val plantToBeChanged = returnTasksFromPlants!![0]
        //assertEquals(4, plantToBeChanged.daysUncompleted)
        //plantToBeChanged.daysUncompleted = 0
        //viewModel.updateTask(plantToBeChanged)
        //returnTasksFromPlants = tasksDao.getAllTasks().first()
      //  assertEquals(0, returnTasksFromPlants!![0].daysUncompleted)
      //  plantToBeChanged.daysUntilNextCycle = plantToBeChanged.cycleLength
      //  viewModel.updateTask(plantToBeChanged)
      //  assertEquals(plantToBeChanged.cycleLength, plantToBeChanged.daysUntilNextCycle)
    }
}

