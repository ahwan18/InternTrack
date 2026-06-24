package com.ahwan.interntrack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahwan.interntrack.data.ApplicationRepository

class ApplicationViewModelFactory(
    private val repository: ApplicationRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApplicationViewModel::class.java)) {
            return ApplicationViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}