package com.ahwan.interntrack.viewmodel

import com.ahwan.interntrack.data.ApplicationEntity

data class ApplicationUiState (
    val applications: List<ApplicationEntity> = emptyList(),
    val selectedStatus: String = "All",
    val isLoading: Boolean = false
)