package com.ahwan.interntrack.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "applications")
data class ApplicationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val companyName: String,
    val position: String,
    val location: String,
    val applicationDate: Long,
    val deadlineDate: Long?,
    val status: String,
    val notes: String
)
