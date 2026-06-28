package com.ahwan.interntrack.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahwan.interntrack.data.ApplicationEntity
import com.ahwan.interntrack.viewmodel.ApplicationViewModel
import com.ahwan.interntrack.util.formatDate
import com.ahwan.interntrack.util.getDaysLeftText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationDetailScreen(
    applicationId: Int,
    viewModel: ApplicationViewModel,
    onBackClick: () -> Unit,
    onEditClick: (Int) -> Unit,
    onDeleted: () -> Unit
    ) {
    val applicationState = produceState<ApplicationEntity?>(initialValue = null, applicationId) {
        value = viewModel.getApplicationById(applicationId)
    }

    val application = applicationState.value

    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(text = "Application Detail")
                }
            )
        }
    ) { paddingValues ->
        if (application == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Text(text = "Application Not Found.")
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedButton(
                    onClick = onBackClick
                ) {
                    Text(text = "Back")
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = application.companyName,
                            style = MaterialTheme.typography.headlineSmall
                        )

                        DetailItem(
                            label = "Position",
                            value = application.position
                        )

                        DetailItem(
                            label = "Location",
                            value = application.location
                        )

                        DetailItem(
                            label = "Status",
                            value = application.status
                        )

                        DetailItem(
                            label = "Deadline",
                            value = formatDate(application.deadlineDate)
                        )

                        DetailItem(
                            label = "Time Left",
                            value = getDaysLeftText(application.deadlineDate)
                        )

                        DetailItem(
                            label = "Notes",
                            value = application.notes
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = {
                            onEditClick(application.id)
                        }
                    ) {
                        Text(text = "Edit")
                    }

                    OutlinedButton(
                        onClick = {
                            showDeleteDialog = true
                        }
                    ) {
                        Text(text = "Delete")
                    }
                }

                OutlinedButton(
                    onClick = onBackClick
                ) {
                    Text(text = "Back")
                }
            }
        }
    }

    if (showDeleteDialog && application != null) {
        AlertDialog(
            onDismissRequest = {
                showDeleteDialog = false
            },
            title = {
                Text(text = "Delete Application?")
            },
            text = {
                Text(
                    text = "Are you sure you want to delete ${application.companyName} application?"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.deleteApplication(application)
                        showDeleteDialog = false
                        onDeleted()
                    }
                ) {
                    Text(text = "Delete")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                    }
                ) {
                    Text(text = "cancel")
                }
            }
        )
    }
}

@Composable
private fun DetailItem(
    label: String,
    value: String
) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
