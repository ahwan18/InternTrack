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
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ahwan.interntrack.ui.component.ApplicationCard
import com.ahwan.interntrack.viewmodel.ApplicationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationListScreen(
    viewModel: ApplicationViewModel,
    onAddApplicationClick: () -> Unit
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
                Text(text = "+")
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
                StatusFilterSection(
                    selectedStatus = uiState.selectedStatus,
                    onStatusSelected = viewModel::updateSelectedStatus
                )
            }

            item {
                Button(
                    onClick = {
                        viewModel.insertSampleApplications()
                    }
                ) {
                    Text(text = "Insert Sample Data")
                }
            }

            item {
                OutlinedButton(
                    onClick = {
                        viewModel.deleteAllApplications()
                    }
                ) {
                    Text(text = "Clear Data")
                }
            }

            if (uiState.isLoading) {
                item {
                    CircularProgressIndicator()
                }
            } else if (uiState.applications.isEmpty()) {
                item {
                    Text(text = "No application yet. Add sample data to test Room.")
                }
            } else {
                items(uiState.applications) { application ->
                    ApplicationCard(application = application)
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
        "interview",
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