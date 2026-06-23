package com.ahwan.interntrack.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahwan.interntrack.model.ApplicationStatus

@Composable
fun StatusChip(
    status: ApplicationStatus,
    modifier: Modifier = Modifier
) {
    AssistChip(
        onClick = {},
        label = {
            Text(text = status.label)
        },
        modifier = modifier.padding(top = 8.dp)
    )
}