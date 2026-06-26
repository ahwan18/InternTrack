package com.ahwan.interntrack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ahwan.interntrack.ui.screen.AddApplicationScreen
import com.ahwan.interntrack.ui.screen.ApplicationDetailScreen
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
                },
                onApplicationClick = { applicationId ->
                    navController.navigate(AppRoute.detailRoute(applicationId))
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

        composable(
            route = "${AppRoute.DETAIL_APPLICATION}/{applicationId}",
            arguments = listOf(
                navArgument("applicationId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val applicationId = backStackEntry.arguments?.getInt("applicationId") ?: return@composable

            ApplicationDetailScreen(
                applicationId = applicationId,
                viewModel = viewModel,
                onBackClick = {
                    navController.popBackStack()
                },
                onEditClick = { id ->
                    navController.navigate(AppRoute.editRoute(id))
                },
                onDeleted = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = "${AppRoute.EDIT_APPLICATION}/{applicationId}",
            arguments = listOf(
                navArgument("applicationId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val applicationId = backStackEntry.arguments?.getInt("applicationId") ?: return@composable

            AddApplicationScreen(
                viewModel = viewModel,
                applicationId = applicationId,
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
