package com.ahwan.interntrack.navigation

object AppRoute {
    const val DASHBOARD = "dashboard"
    const val ADD_APPLICATION = "add_application"
    const val DETAIL_APPLICATION = "detail_application"
    const val EDIT_APPLICATION = "edit_application"

    fun detailRoute(applicationId: Int): String {
        return "$DETAIL_APPLICATION/$applicationId"
    }

    fun editRoute(applicationId: Int): String {
        return "$EDIT_APPLICATION/$applicationId"
    }
}