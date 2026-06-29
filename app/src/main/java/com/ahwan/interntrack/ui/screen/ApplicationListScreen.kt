package com.ahwan.interntrack.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.runtime.getValue
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ahwan.interntrack.ui.component.ApplicationCard
import com.ahwan.interntrack.viewmodel.ApplicationViewModel
import com.ahwan.interntrack.ui.component.DashboardSection
import com.ahwan.interntrack.ui.component.EmptyState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationListScreen(
    viewModel: ApplicationViewModel,
    onAddApplicationClick: () -> Unit,
    onApplicationClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Column {
                        Text(text = "InternTrack")
                        Text(text = "Track your internship journey")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddApplicationClick
            ) {
                Text(text = "Add")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                DashboardSection(uiState = uiState)
            }

            item {
                StatusFilterSection(
                    selectedStatus = uiState.selectedStatus,
                    onStatusSelected = viewModel::updateSelectedStatus
                )
            }

            if (uiState.isLoading) {
                item {
                    CircularProgressIndicator()
                }
            } else if (uiState.applications.isEmpty()) {
                item {
                    EmptyState(
                        title = if (uiState.selectedStatus == "All") {
                            "No applications yet"
                        } else {
                            "No ${uiState.selectedStatus} applications"
                        },
                        message = if (uiState.selectedStatus == "All") {
                            "Start tracking your internship journey by adding your first application."
                        } else {
                            "Try selecting another status filter or add a new application."
                        },
                        buttonText = "Add application",
                        onButtonClick = onAddApplicationClick
                    )
                }
            } else {
                items(uiState.applications) { application ->
                    ApplicationCard(
                        application = application,
                        onClick = {
                            onApplicationClick(application.id)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun StatusFilterSection(
    selectedStatus: String,
    onStatusSelected: (String) -> Unit
) {
    val statuses = listOf(
        "All",
        "Saved",
        "Applied",
        "Interview",
        "Offer",
        "Rejected"
    )

    androidx.compose.foundation.lazy.LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(statuses) { status ->
            AssistChip(
                onClick = {
                    onStatusSelected(status)
                },
                label = {
                    Text(
                        text = if(status == selectedStatus) {
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