package com.ahwan.interntrack.model

data class InternshipApplication(
    val id: Int,
    val compnanyName: String,
    val position: String,
    val location: String,
    val status: ApplicationStatus,
    val deadline: String,
    val notes: String
)