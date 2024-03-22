package com.example.plantcare.worker

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import javax.inject.Singleton

@Singleton
class Initializer(context : Context) {

    private val workManager = WorkManager.getInstance(context)

    companion object {
        const val WORK_MANAGER_TAG = "WORK_MANAGER_DOWNLOAD_TAG"
    }

    operator fun invoke(){
        if(isWorkerAlreadyRunning())
            return

     //   val request = PeriodicWorkRequestBuilder<TasksWorker>(1, TimeUnit.HOURS)
     //       .addTag(WORK_MANAGER_TAG)
     //       .build()
        val request = OneTimeWorkRequestBuilder<TasksWorker>()
            .addTag(WORK_MANAGER_TAG)
            .build()

        workManager.enqueue(request)
    }

    private fun isWorkerAlreadyRunning(): Boolean {
        val workInfo = workManager.getWorkInfosByTag(WORK_MANAGER_TAG).get()
        workInfo.forEach { work ->
            if(work.state == WorkInfo.State.ENQUEUED || work.state == WorkInfo.State.RUNNING)
                return true
        }
        return false
    }
}
