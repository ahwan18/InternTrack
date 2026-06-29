package com.ahwan.interntrack.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahwan.interntrack.data.ApplicationEntity
import com.ahwan.interntrack.util.formatDate
import com.ahwan.interntrack.util.getDaysLeftText
import com.ahwan.interntrack.util.isDueSoon
import com.ahwan.interntrack.util.getProgressLabel
import com.ahwan.interntrack.util.getProgressPercentage

@Composable
fun ApplicationCard(
    application: ApplicationEntity,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val progress = getProgressPercentage(application.status) / 100f

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = application.companyName,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = application.position,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                StatusChip(status = application.status)

                if (isDueSoon(application.deadlineDate)) {
                    DueSoonChip()
                }
            }

            Text(
                text = application.location,
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = "Deadline : ${formatDate(application.deadlineDate)} · ${getDaysLeftText(application.deadlineDate)}",
                style = MaterialTheme.typography.bodySmall
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                LinearProgressIndicator(
                    progress = {
                        progress
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "${getProgressPercentage(application.status)}% · ${getProgressLabel(application.status)}",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "Tap to view details",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}