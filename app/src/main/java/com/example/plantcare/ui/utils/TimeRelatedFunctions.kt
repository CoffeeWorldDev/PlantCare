package com.example.plantcare.ui.utils

import android.annotation.SuppressLint
import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit


/**
     * Returns the start of today in milliseconds
     */
    fun getDateInMillis(addedDay: Int = 0): Date {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val date = cal.get(Calendar.DATE)
        cal.clear()
        cal.set(year, month, date)
        cal.add(Calendar.DAY_OF_YEAR, addedDay)
        return cal.time
    }

@SuppressLint("SimpleDateFormat")
fun getDateInString(day: Int = 0): String {
    val time = Calendar.getInstance().time
    val ca = Calendar.getInstance()
    ca.time = time
    ca.add(Calendar.DAY_OF_YEAR, day)
    val formatter = SimpleDateFormat("MMM\ndd")
    return formatter.format(ca.time)
}

    fun getElapsedTime(previousDate: Date): Int {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val changedOldDate = previousDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
            val today = LocalDate.now()
            return Period.between(changedOldDate, today).days
        }
        val diff: Long = previousDate.time - getDateInMillis(0).time
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS).toInt()
    }



