package com.ahwan.interntrack.util

fun getProgressPercentage(status: String): Int {
    return when (status) {
        "Saved" -> 20
        "Applied" -> 40
        "Interview" -> 70
        "Offer" -> 100
        "Rejected" -> 0
        else -> 0
    }
}

fun getProgressLabel(status: String): String {
    return when (status) {
        "Saved" -> "Application saved"
        "Applied" -> "Waiting for response"
        "Interview" -> "Interview stage"
        "Offer" -> "Offer received"
        "Rejected" -> "Application closed"
        else -> "Unknown status"
    }
}