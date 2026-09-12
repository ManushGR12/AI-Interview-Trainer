package com.aiinterviewtrainer.util

import java.text.SimpleDateFormat
import java.util.*

fun Long.toDateString(format: String = "dd MMM yyyy"): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(format, Locale.getDefault())
    return formatter.format(date)
}

fun Long.toReadableDuration(): String {
    val minutes = this / 60
    val seconds = this % 60
    return String.format("%02d:%02d", minutes, seconds)
}
