package com.example.plantcare.ui.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun getElapsedTime(oldDate: Date): Int {
        // parse the date with a suitable formatter
       val changedOldDate = oldDate.toInstant()
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



