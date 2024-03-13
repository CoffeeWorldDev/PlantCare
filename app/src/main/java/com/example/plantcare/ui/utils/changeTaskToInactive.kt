package com.example.plantcare.ui.utils

import com.example.plantcare.data.model.Tasks
import java.util.Date

fun changeTaskToInactive(task: Tasks, date: Date) : Tasks {
    val passedDays = getElapsedTime(date)
    task.isActive = false
    task.lastCompleted = date
    task.daysUncompleted = passedDays
    task.urgency = checkUrgencyLevel(passedDays)
    task.nextCycleDate = getDateInMillis(task.cycleLength - passedDays)
    task.daysUntilNextCycle = task.cycleLength - passedDays
    return task
}