package com.example.plantcare.repository.ui

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.plantcare.data.db.PlantsDatabase
import com.example.plantcare.data.model.Plants
import com.example.plantcare.domain.useCase.plants.AddPlantsUseCase
import com.example.plantcare.domain.useCase.plants.GetPlantsAndTasksUseCase
import com.example.plantcare.domain.useCase.plants.GetActivePlantsUseCase
import com.example.plantcare.domain.useCase.tasks.UpdateTasksUseCase
import com.example.plantcare.ui.home.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import javax.inject.Inject

//TODO try testing again
@HiltAndroidTest
class HomeViewModelTest {


    private lateinit var database: PlantsDatabase
    private lateinit var viewModel: HomeViewModel
    private val hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule: RuleChain = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)

    @Inject
    lateinit var getActivePlantsUseCase: GetActivePlantsUseCase

    @Inject
    lateinit var updateTasksUseCase: UpdateTasksUseCase


    private val plant = Plants(1, "Apples", "cactus",
        "bunnie ear", "bedroom",
        "one year", "dfjdls", mapOf("34/43/23" to "dfsklj"),
        "winter")

    private val mockReturn = listOf<Plants>(plant, plant)
    @Before
    fun setUp() {
        hiltRule.inject()
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, PlantsDatabase::class.java).build()
       //addPlantsUseCase = Mockito.mock(AddPlantsUseCase::class.java)
       //getPlantsUseCase = Mockito.mock(GetPlantsUseCase::class.java)
       //getPlantsAndTasksUseCase = Mockito.mock(GetPlantsAndTasksUseCase::class.java)
       //updateTasksUseCase = Mockito.mock(UpdateTasksUseCase::class.java)
        //viewModel = PlantsViewModel(addPlantsUseCase, getPlantsUseCase)

        viewModel = HomeViewModel(getActivePlantsUseCase, updateTasksUseCase)

      //  Mockito.`when`(getActivePlantsUseCase.execute()).thenReturn(flowOf(listOf<Any>()) as Flow<List<Plants>>?)
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun plantsViewModel_CallPlantState_NotNull() {
 //       var currentUiState = viewModel.plantsState.value
 //       val currentValue = currentUiState.plantsList
 //     //  runBlocking {
 //     //  }
 //       assertNotNull(currentValue)
    }

    @Test
    fun plantsViewModel_AddPlant_Success(){
  //      var addPlant = viewModel.addPlant(plant)

       // assertTrue(addPlant)
    }
}