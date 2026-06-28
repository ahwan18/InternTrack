package com.ahwan.interntrack.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.ahwan.interntrack.viewmodel.ApplicationUiState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DashboardSection(
    uiState: ApplicationUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SummaryCard(
            title = "Total Applications",
            value = uiState.totalApplications.toString(),
            subtitle = "All tracked applications"
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SummaryCard(
                title = "Interview Rate",
                value = "${uiState.interviewRate}%",
                subtitle = "${uiState.interviewCount} interview stage",
                modifier = Modifier.weight(1f)
            )

            SummaryCard(
                title = "Offers",
                value = uiState.offerCount.toString(),
                subtitle = "${uiState.offerRate}% offer rate",
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SummaryCard(
                title = "Applied",
                value = uiState.appliedCount.toString(),
                subtitle = "Currently applied",
                modifier = Modifier.weight(1f)
            )

            SummaryCard(
                title = "Rejected",
                value = uiState.rejectedCount.toString(),
                subtitle = "Closed opportunities",
                modifier = Modifier.weight(1f)
            )
        }
    }
}