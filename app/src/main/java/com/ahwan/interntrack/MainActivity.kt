package com.ahwan.interntrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.ahwan.interntrack.data.ApplicationRepository
import com.ahwan.interntrack.data.DatabaseProvider
import com.ahwan.interntrack.ui.screen.ApplicationListScreen
import com.ahwan.interntrack.ui.theme.InternTrackTheme
import com.ahwan.interntrack.viewmodel.ApplicationViewModel
import com.ahwan.interntrack.viewmodel.ApplicationViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var applicationViewModel: ApplicationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = DatabaseProvider.getDatabase(applicationContext)
        val repository = ApplicationRepository(database.applicationDao())
        val factory = ApplicationViewModelFactory(repository)

        applicationViewModel = ViewModelProvider(
            this,
            factory
        )[ApplicationViewModel::class.java]

        setContent {
            InternTrackTheme {
                ApplicationListScreen(
                    viewModel = applicationViewModel
                )
            }
        }
    }
}