package com.jmabilon.mymovietheater.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.jmabilon.mymovietheater.navigation.ScreensRoute
import com.jmabilon.mymovietheater.presentation.ui.onboarding.OnBoardingScreen

fun NavGraphBuilder.onBoardingGraph(navHostController: NavHostController) {
    composable(ScreensRoute.OnBoardingScreen.route) {
        OnBoardingScreen(navHostController)
    }
}