package com.example.plantcare.ui.utils


//todo if something crashes it's this bitch not changing in java lowercase u
enum class Urgency {
    Normal,
    Late,
    Urgent
}

fun checkUrgencyLevel(daysPassed : Int): String {
    val urgencyLevel : String = when {
        daysPassed < 3 -> Urgency.Normal.toString()
        daysPassed < 5 -> Urgency.Late.toString()
        else -> Urgency.Urgent.toString()
    }
    return urgencyLevel
}