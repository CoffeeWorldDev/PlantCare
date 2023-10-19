package com.example.plantcare.ui.util

import java.util.Calendar
import java.util.Date

/**
 * Returns the start of today in milliseconds
 */
fun getDateInMillis(addedDay: Int = 0): Date {
    val cal = Calendar.getInstance()
    val year = cal.get(Calendar.YEAR)
    val month = cal.get(Calendar.MONTH)
    val date = cal.get(Calendar.DATE)
    cal.add(Calendar.DAY_OF_YEAR, addedDay)
    cal.clear()
    cal.set(year, month, date)
    return cal.time
}

