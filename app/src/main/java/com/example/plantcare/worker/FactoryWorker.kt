package com.example.plantcare.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.plantcare.domain.repository.TasksRepository
import javax.inject.Inject

class CustomWorkerFactory @Inject constructor(
    private val tasksRepository: TasksRepository
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker = TasksWorker(appContext,workerParameters, tasksRepository)
}
