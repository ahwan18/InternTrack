package com.ahwan.interntrack.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahwan.interntrack.data.dummyApplications
import com.ahwan.interntrack.ui.component.ApplicationCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationListScreen() {
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
            items(dummyApplications) { application ->
                ApplicationCard(application = application)
            }
        }
    }
}