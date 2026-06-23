package com.ahwan.interntrack.data

import com.ahwan.interntrack.model.ApplicationStatus
import com.ahwan.interntrack.model.InternshipApplication

val dummyApplications = listOf(
    InternshipApplication(
        id = 1,
        compnanyName = "TokoPedia",
        position = "Android Engineer Intern",
        location = "Jakarta / Hybrid",
        status = ApplicationStatus.APPLIED,
        deadline = "25 Jun 2026",
        notes = "Applied through refferal"
    ),
    InternshipApplication(
        id = 2,
        compnanyName = "Gojek",
        position = "Mobile Developer Intern",
        location = "Jakarta",
        status = ApplicationStatus.INTERVIEW,
        deadline = "28 Jun 2026",
        notes = "Prepare Kotlin and Android architecture questions"
    ),
    InternshipApplication(
        id = 3,
        compnanyName = "Traveloka",
        position = "Software Intern",
        location = "Remote",
        status = ApplicationStatus.SAVED,
        deadline = "30 Jun 2026",
        notes = "Need to update resume before applying"
    )
)