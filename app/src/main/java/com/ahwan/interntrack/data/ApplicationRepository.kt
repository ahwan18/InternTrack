package com.ahwan.interntrack.data

import kotlinx.coroutines.flow.Flow

class ApplicationRepository(
    private val applicationDao: ApplicationDao
) {
    val applications: Flow<List<ApplicationEntity>> =
        applicationDao.getAllApplications()

    suspend fun getApplicationById(id: Int): ApplicationEntity? {
        return applicationDao.getApplicationById(id)
    }

    suspend fun insertApplication(application: ApplicationEntity) {
        applicationDao.insertApplication(application)
    }

    suspend fun updateApplication(application: ApplicationEntity) {
        applicationDao.updateApplication(application)
    }

    suspend fun deleteApplication(application: ApplicationEntity) {
        applicationDao.deleteApplication(application)
    }

    suspend fun deleteAllApplication() {
        applicationDao.deleteAllAplications()
    }
}