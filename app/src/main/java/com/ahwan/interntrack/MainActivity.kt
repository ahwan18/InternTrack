package com.ahwan.interntrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ahwan.interntrack.ui.screen.ApplicationListScreen
import com.ahwan.interntrack.ui.theme.InternTrackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InternTrackTheme {
                ApplicationListScreen()
            }
        }
    }
}