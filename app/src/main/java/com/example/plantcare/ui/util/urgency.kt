package com.example.plantcare.ui.util

import androidx.compose.ui.unit.dp

enum class urgency {
    normal,
    late,
    urgent
}

fun CheckUrgencyLevel(daysPassed : Int): String {
    val urgencyLevel : String = when {
        daysPassed < 3 -> urgency.normal.toString()
        daysPassed < 5 -> urgency.late.toString()
        else -> urgency.urgent.toString()
    }
    return urgencyLevel
}