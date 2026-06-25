package com.ahwan.interntrack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahwan.interntrack.ui.screen.AddApplicationScreen
import com.ahwan.interntrack.ui.screen.ApplicationListScreen
import com.ahwan.interntrack.viewmodel.ApplicationViewModel

@Composable
fun AppNavigation(
    viewModel: ApplicationViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoute.DASHBOARD
    ) {
        composable(AppRoute.DASHBOARD) {
            ApplicationListScreen(
                viewModel = viewModel,
                onAddApplicationClick = {
                    navController.navigate(AppRoute.ADD_APPLICATION)
                }
            )
        }

        composable(AppRoute.ADD_APPLICATION) {
            AddApplicationScreen(
                viewModel = viewModel,
                onBackClick = {
                    navController.popBackStack()
                },
                onApplicationSaved = {
                    navController.popBackStack()
                }
            )
        }
    }
}
