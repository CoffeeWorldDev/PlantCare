package com.example.plantcare.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.plantcare.domain.repository.PlantsRepository
import com.example.plantcare.domain.repository.TasksRepository
import com.example.plantcare.worker.Initializer

class HomeViewModelFactory(private val plantsRepository: PlantsRepository,
                           private val tasksRepository: TasksRepository,
                           private var workerInitializer: Initializer
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(
            plantsRepository,
            tasksRepository,
            workerInitializer
        ) as T
    }
}

//private val getActivePlantsUseCase: GetActivePlantsUseCase,
//private val updateTasksUseCase: UpdateTasksUseCase,
//private val getFutureActivePlantsUseCase: GetFutureActivePlantsUseCase