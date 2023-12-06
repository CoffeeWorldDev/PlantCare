package com.example.plantcare.ui.utils

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
    fun GetDateInMillis(addedDay: Int = 0): Date {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val date = cal.get(Calendar.DATE)
        cal.clear()
        cal.set(year, month, date)
        cal.add(Calendar.DAY_OF_YEAR, addedDay)
        return cal.time
    }

fun GetDateInString(day: Int = 0):String{
    val time = Calendar.getInstance().time
    val ca = Calendar.getInstance()
    ca.time = time
    ca.add(Calendar.DAY_OF_YEAR, day)
    val formatter = SimpleDateFormat("MMM\ndd")
    val current = formatter.format(ca.time)
    //Log.d("Click", current)
    return current
}

    fun getElapsedTime(previousDate: Date): Int {
        // parse the date with a suitable formatter
        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val changedOldDate = previousDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
            // val from = LocalDate.parse("04112005", DateTimeFormatter.ofPattern("ddMMyyyy"))
            // get today's date
            val today = LocalDate.now()
            // calculate the period between those two
            return Period.between(changedOldDate, today).days
            // and print it in a human-readable way
            // println("The difference between " + from.format(DateTimeFormatter.ISO_LOCAL_DATE)
            //         + " and " + today.format(DateTimeFormatter.ISO_LOCAL_DATE) + " is "
            //         + period.years + " years, " + period.months + " months and "
            //         + period.days + " days")
        }

        val diff: Long = previousDate.time - GetDateInMillis(0).time
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS).toInt()
    }



