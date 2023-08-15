package com.jmabilon.mymovietheater.navigation

sealed class ScreensRoute(val route: String) {
    // On Boarding
    object OnBoardingScreen : ScreensRoute("on_boarding_screen")

    // Home
    object HomeGraph : ScreensRoute("home_graph")
    object HomeScreen : ScreensRoute("home_screen")
    object HomeDetailScreen : ScreensRoute("home_detail_screen")
}
