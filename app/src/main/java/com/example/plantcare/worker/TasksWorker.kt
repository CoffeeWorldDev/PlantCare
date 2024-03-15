package com.example.plantcare.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.domain.repository.TasksRepository
import com.example.plantcare.ui.utils.getDateInMillis
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@TasksWorker.HiltWorker
class TasksWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val tasksRepository: TasksRepository
): CoroutineWorker(appContext, workerParams) {
    annotation class HiltWorker

    override suspend fun doWork(): Result {
        TODO("Not yet implemented")

    }

    private suspend fun controlTasks() {

        val tasksList = mutableListOf<Tasks>()
        tasksRepository.getAllTasks().collect{
            if(it?.isNotEmpty() == true){
                it.forEach{task ->
                    tasksList.add(task)
                }
            }
        }
        if (tasksList.isNotEmpty()){
            tasksList.forEach{
                //check if these match or if the dates are in different times/format
                if (it.nextCycleDate == getDateInMillis()){

                }
            }
        }

    }

}
