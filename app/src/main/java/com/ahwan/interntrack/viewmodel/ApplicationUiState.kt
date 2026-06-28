package com.ahwan.interntrack.viewmodel

import com.ahwan.interntrack.data.ApplicationEntity

data class ApplicationUiState (
    val applications: List<ApplicationEntity> = emptyList(),
    val allApplications: List<ApplicationEntity> = emptyList(),
    val selectedStatus: String = "All",
    val isLoading: Boolean = false,
    val totalApplications: Int = 0,
    val savedCount: Int = 0,
    val appliedCount: Int = 0,
    val interviewCount: Int = 0,
    val offerCount: Int = 0,
    val rejectedCount: Int = 0,
    val interviewRate: Int = 0,
    val offerRate: Int = 0
)