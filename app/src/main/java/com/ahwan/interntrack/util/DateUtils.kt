package com.ahwan.interntrack.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

fun formatDate(timestamp: Long?): String {
    if (timestamp == null) {
        return "No deadline"
    }

    val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return formatter.format(Date(timestamp))
}

fun isDueSoon(deadlineTimestamp: Long?): Boolean {
    if (deadlineTimestamp == null) {
        return false
    }

    val currentTime = System.currentTimeMillis()
    val difference = deadlineTimestamp - currentTime
    val daysLeft = TimeUnit.MILLISECONDS.toDays(difference)

    return daysLeft in 0..3
}

fun getDaysLeftText(deadlineTimestamp: Long?): String {
    if (deadlineTimestamp == null) {
        return "No deadline"
    }

    val currentTime = System.currentTimeMillis()
    val difference = deadlineTimestamp - currentTime
    val daysLeft = TimeUnit.MILLISECONDS.toDays(difference)

    return when {
        daysLeft < 0 -> "Deadline passed"
        daysLeft == 0L -> "Due today"
        daysLeft == 1L -> "Due tomorrow"
        else -> "$daysLeft days left"
    }
}