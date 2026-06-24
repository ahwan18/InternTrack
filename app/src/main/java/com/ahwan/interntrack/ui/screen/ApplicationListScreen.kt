package com.ahwan.interntrack.ui.screen

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahwan.interntrack.data.dummyApplications
import com.ahwan.interntrack.ui.component.ApplicationCard
import com.ahwan.interntrack.viewmodel.ApplicationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationListScreen(
    viewModel: ApplicationViewModel
) {
    val applications by viewModel.applications.collectAsState()

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Column() {
                        Text(text = "InternTrack")
                        Text(text = "Track your internship journey")
                    }
                }
            )
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

            if (applications.isEmpty()) {
                item {
                    Text(text = "No application yet. Add sample data to test Room.")
                }
            } else {
                items(applications) { application ->
                    ApplicationCard(application = application)
                }
            }
        }
    }
}