package com.ahwan.interntrack.ui.component

import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun StatusChip(
    status: String
) {
    val containerColor = when (status) {
        "Saved" -> MaterialTheme.colorScheme.surfaceVariant
        "Applied" -> MaterialTheme.colorScheme.primaryContainer
        "Interview" -> MaterialTheme.colorScheme.tertiaryContainer
        "Offer" -> MaterialTheme.colorScheme.secondaryContainer
        "Rejected" -> MaterialTheme.colorScheme.errorContainer
        else -> MaterialTheme.colorScheme.surfaceVariant
    }

    val labelColor = when (status) {
        "Rejected" -> MaterialTheme.colorScheme.onErrorContainer
        "Applied" -> MaterialTheme.colorScheme.onPrimaryContainer
        "Interview" -> MaterialTheme.colorScheme.onTertiaryContainer
        "Offer" -> MaterialTheme.colorScheme.onSecondaryContainer
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    AssistChip(
        onClick = {},
        label = {
            Text(text = status)
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = containerColor,
            labelColor = labelColor
        )
    )
}