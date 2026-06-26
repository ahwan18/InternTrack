package com.ahwan.interntrack.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahwan.interntrack.viewmodel.ApplicationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddApplicationScreen(
    viewModel: ApplicationViewModel,
    applicationId: Int? = null,
    onBackClick:() -> Unit,
    onApplicationSaved: () -> Unit
) {
    val isEditMode = applicationId != null

    var companyName by remember { mutableStateOf("") }
    var position by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var selectedStatus by remember { mutableStateOf("Saved") }
    var notes by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    var applicationDate by remember {
        mutableLongStateOf(System.currentTimeMillis())
    }

    var deadlineDate by remember {
        mutableStateOf<Long?>(null)
    }

    LaunchedEffect(applicationId) {
        if (applicationId != null) {
            val application = viewModel.getApplicationById(applicationId)

            if (application != null) {
                companyName = application.companyName
                position = application.position
                location = application.location
                selectedStatus = application.status
                notes = application.notes
                applicationDate = application.applicationDate
                deadlineDate = application.deadlineDate
            }
        }
    }

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = if (isEditMode) {
                            "Edit Application"
                        } else {
                            "Add Application"
                        }
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = companyName,
                onValueChange = {
                    companyName = it
                    errorMessage = null
                },
                label = {
                    Text(text = "Company Name")
                },
                singleLine = true
            )

            OutlinedTextField(
                value = position,
                onValueChange = {
                    position = it
                    errorMessage = null
                },
                label = {
                    Text(text = "Position")
                },
                singleLine = true
            )

            OutlinedTextField(
                value = location,
                onValueChange = {
                    location = it
                },
                label = {
                    Text(text = "Location")
                },
                singleLine = true
            )

            Text(text = "Status")

            StatusPicker(
                selectedStatus = selectedStatus,
                onStatusSelected = {
                    selectedStatus = it
                }
            )

            OutlinedTextField(
                value = notes,
                onValueChange = {
                    notes = it
                },
                label = {
                    Text(text = "Notes")
                },
                minLines = 3
            )

            if (errorMessage != null) {
                Text(text = errorMessage!!)
            }

            Button(
                onClick = {
                    if (companyName.isBlank() || position.isBlank()) {
                        errorMessage = "Company name and position cannot be empty."
                        return@Button
                    }

                    if (isEditMode && applicationId != null) {
                        viewModel.updateApplication(
                            id = applicationId,
                            companyName = companyName,
                            position = position,
                            location = location.ifBlank { "Not specified" },
                            status = selectedStatus,
                            notes = notes.ifBlank { "No notes" },
                            applicationDate = applicationDate,
                            deadlineDate = deadlineDate
                        )
                    } else {
                        viewModel.insertApplication(
                            companyName = companyName,
                            position = position,
                            location = location.ifBlank { "Not Specified" },
                            status = selectedStatus,
                            notes = notes.ifBlank { "No notes" }
                        )
                    }

                    onApplicationSaved()
                }
            ) {
                Text(
                    text = if (isEditMode) {
                        "Update Application"
                    } else {
                        "Save Application"
                    }
                )
            }

            OutlinedButton(
                onClick = onBackClick
            ) {
                Text(text = "Cancel")
            }
        }
    }
}

@Composable
private fun StatusPicker(
    selectedStatus: String,
    onStatusSelected: (String) -> Unit
) {
    val statuses = listOf(
        "Saved",
        "Applied",
        "Interview",
        "Offer",
        "Rejected"
    )

    androidx.compose.foundation.lazy.LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 4.dp)
    ) {
        items(statuses) { status ->
            AssistChip(
                onClick = {
                    onStatusSelected(status)
                },
                label = {
                    Text(
                        text = if (status == selectedStatus) {
                            "✓ $status"
                        } else {
                            status
                        }
                    )
                }
            )
        }
    }
}
