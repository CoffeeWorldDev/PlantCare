package com.example.plantcare.ui.plantsGallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.plantcare.domain.repository.PlantsRepository

@Suppress("UNCHECKED_CAST")
class PlantsGalleryViewModelFactory(private val plantsRepository: PlantsRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlantsGalleryViewModel(
            plantsRepository
        ) as T
    }
}