package com.jmabilon.mymovietheater.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jmabilon.mymovietheater.navigation.ScreensRoute
import com.jmabilon.mymovietheater.presentation.ui.home.detail.HomeDetailScreen
import com.jmabilon.mymovietheater.presentation.ui.home.HomeScreen

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation(
        startDestination = ScreensRoute.HomeScreen.route,
        route = ScreensRoute.HomeGraph.route
    ) {
        composable(ScreensRoute.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(ScreensRoute.HomeDetailScreen.route) {
            HomeDetailScreen(navController)
        }
    }
}