package com.example.plantcare.ui.plantCreationEdit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.plantcare.domain.repository.PlantsRepository
import com.example.plantcare.domain.repository.TasksRepository

class PlantCreationEditViewModelFactory(private val plantsRepository: PlantsRepository,
                                        private val tasksRepository: TasksRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlantCreationEditViewModel(
            plantsRepository,
            tasksRepository
        ) as T
    }
}
