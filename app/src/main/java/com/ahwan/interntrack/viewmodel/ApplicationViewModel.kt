package com.ahwan.interntrack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahwan.interntrack.data.ApplicationEntity
import com.ahwan.interntrack.data.ApplicationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class ApplicationViewModel(
    private val repository: ApplicationRepository
) : ViewModel() {

    private val selectedStatus = MutableStateFlow("All")

    val uiState: StateFlow<ApplicationUiState> =
        combine(
            repository.applications,
            selectedStatus
        ) { applications, status ->
            val filteredApplication = if (status == "All") {
                applications
            } else {
                applications.filter { application ->
                    application.status == status
                }
            }

            val totalApplications = applications.size
            val savedCount = applications.count { it.status == "Saved" }
            val appliedCount = applications.count { it.status == "Applied" }
            val interviewCount = applications.count { it.status == "Interview" }
            val offerCount = applications.count { it.status == "Offer" }
            val rejectedCount = applications.count { it.status == "Rejected" }

            val interviewRate = if (totalApplications > 0) {
                ((interviewCount.toDouble() / totalApplications) * 100).toInt()
            } else {
                0
            }

            val offerRate = if (totalApplications > 0) {
                ((offerCount.toDouble() / totalApplications) * 100).toInt()
            } else {
                0
            }

            ApplicationUiState(
                applications = filteredApplication,
                allApplications = applications,
                selectedStatus = status,
                isLoading = false,
                totalApplications = totalApplications,
                savedCount = savedCount,
                appliedCount = appliedCount,
                interviewCount = interviewCount,
                offerCount = offerCount,
                rejectedCount = rejectedCount,
                interviewRate = interviewRate,
                offerRate = offerRate
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ApplicationUiState(isLoading = true)
        )

    fun updateSelectedStatus(status: String) {
        selectedStatus.value = status
    }

    suspend fun getApplicationById(id: Int): ApplicationEntity? {
        return repository.getApplicationById(id)
    }

    fun insertApplication(
        companyName: String,
        position: String,
        location: String,
        status: String,
        notes: String,
        deadlineDate: Long? = null
    ) {
        viewModelScope.launch {
            repository.insertApplication(
                ApplicationEntity(
                    companyName = companyName,
                    position = position,
                    location = location,
                    applicationDate = System.currentTimeMillis(),
                    deadlineDate = deadlineDate,
                    status = status,
                    notes = notes
                )
            )
        }
    }

    fun updateApplication(
        id: Int,
        companyName: String,
        position: String,
        location: String,
        status: String,
        notes: String,
        applicationDate: Long,
        deadlineDate: Long?
    ) {
        viewModelScope.launch {
            repository.updateApplication(
                ApplicationEntity(
                    id = id,
                    companyName = companyName,
                    position = position,
                    location = location,
                    applicationDate = applicationDate,
                    deadlineDate = deadlineDate,
                    status = status,
                    notes = notes
                )
            )
        }
    }

    fun deleteApplication(application: ApplicationEntity) {
        viewModelScope.launch {
            repository.deleteApplication(application)
        }
    }
}