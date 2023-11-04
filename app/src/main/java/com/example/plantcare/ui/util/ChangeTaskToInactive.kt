package com.example.plantcare.ui.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.plantcare.data.model.Tasks
import java.util.Date

//TODO check about build versions
@RequiresApi(Build.VERSION_CODES.O)
public fun ChangeTaskToInactive(task: Tasks, date: Date) : Tasks {
    val passedDays = getElapsedTime(date)
    task.isActive = false
    task.lastCompleted = date
    task.daysUncompleted = passedDays
    task.urgency = CheckUrgencyLevel(passedDays)
    task.nextCycleDate = GetDateInMillis(task.cycleLength - passedDays)
    task.daysUntilNextCycle = task.cycleLength - passedDays
    return task
}