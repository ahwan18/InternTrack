package com.ahwan.interntrack.model

enum class ApplicationStatus(
    val label: String
) {
    SAVED("Saved"),
    APPLIED("Applied"),
    INTERVIEW("Interview"),
    OFFER("Offer"),
    REJECTED("Rejected")
}