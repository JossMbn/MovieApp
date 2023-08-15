package com.jmabilon.mymovietheater.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.jmabilon.mymovietheater.navigation.graph.homeGraph
import com.jmabilon.mymovietheater.navigation.graph.onBoardingGraph

@Composable
fun SetupNavGraph(navHostController: NavHostController, startDestination: ScreensRoute) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination.route
    ) {
        onBoardingGraph(navHostController)
        homeGraph(navHostController)
    }
}