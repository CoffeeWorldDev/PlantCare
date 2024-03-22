package com.example.plantcare.ui

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.work.Configuration
import com.example.plantcare.worker.CustomWorkerFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

const val NOTIFICATION_CHANNEL_ID = "PlantCare notification channel"

@HiltAndroidApp
class App : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: CustomWorkerFactory

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel()
        }
    }

    override val workManagerConfiguration: Configuration
        get() =  Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    //todo fix the notification channel
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(){
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            "Downloading file",
            NotificationManager.IMPORTANCE_HIGH
        ).let {
            it.description = "Downloading file and saving it"
            it.enableLights(true)
            it.lightColor = Color.RED
            it.enableVibration(true)
            it.vibrationPattern = longArrayOf(100,200,300,400,500,400,300,200,400)
            it
        }
        notificationManager.createNotificationChannel(channel)
    }

    //override fun getWorkManagerConfiguration(): Configuration =
    //    Configuration.Builder()
    //        .setWorkerFactory(workerFactory)
    //        .build()
    //
    //@Inject lateinit var workerFactory: HiltWorkerFactory

}