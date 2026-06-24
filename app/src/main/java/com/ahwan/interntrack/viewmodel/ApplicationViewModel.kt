package com.ahwan.interntrack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahwan.interntrack.data.ApplicationEntity
import com.ahwan.interntrack.data.ApplicationRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ApplicationViewModel(
    private val repository: ApplicationRepository
) : ViewModel() {

    val applications: StateFlow<List<ApplicationEntity>> =
        repository.applications.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun insertSampleApplications() {
        viewModelScope.launch {
            repository.insertApplication(
                ApplicationEntity(
                    companyName = "TokoPedia",
                    position = "Android Engineer Intern",
                    location = "Jakarta / Hybrid",
                    applicationDate = System.currentTimeMillis(),
                    deadlineDate = System.currentTimeMillis() + 3L * 24 * 60 * 60 * 1000,
                    status = "Applied",
                    notes = "Applied through referral"
                )
            )

            repository.insertApplication(
                ApplicationEntity(
                    companyName = "Gojek",
                    position = "Mobile Developer Intern",
                    location = "Jakarta",
                    applicationDate = System.currentTimeMillis(),
                    deadlineDate = System.currentTimeMillis() + 5L * 24 * 60 * 60 * 1000,
                    status = "Interview",
                    notes = "Prepare Kotlin and Android architecture questions"
                )
            )
        }
    }

    fun deleteAllApplications() {
        viewModelScope.launch {
            repository.deleteAllApplication()
        }
    }
}