package com.example.plantcare.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.domain.repository.TasksRepository
import com.example.plantcare.ui.utils.getDateInMillis
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException

@TasksWorker.HiltWorker
class TasksWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted private val workerParams: WorkerParameters,
    private val tasksRepository: TasksRepository
): CoroutineWorker(appContext, workerParams) {
    annotation class HiltWorker

    private val TAG = "DownloadFileWorker"

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
                try {
                    controlTasks()
                    Log.e(TAG, "worker working")
                } catch (ex: CancellationException) {
                 //   cleanUp(download!!)
                } catch (ex: Exception) {
                    println("$TAG: Exception : \n ${ex.message}")
                    ex.printStackTrace()
                }
                //finally {
                //    downloadsQueue = getPendingDownloadsAsQueueUseCase()
                //}
            Result.success()

            //   override suspend fun doWork(): Result {
//
            //       controlTasks()
            //       TODO("Not yet implemented")
//
            //   }
        }


    }
    private suspend fun controlTasks() : Boolean {
        Log.e(TAG, "worker working")
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
                    it.isActive = true
                }
            }
            tasksList.forEach {
                if (it.isActive){
                    return true
                }
            }
        }
        return false
    }

}

