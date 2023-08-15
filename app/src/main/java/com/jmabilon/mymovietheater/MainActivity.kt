package com.jmabilon.mymovietheater

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jmabilon.mymovietheater.navigation.ScreensRoute
import com.jmabilon.mymovietheater.navigation.SetupNavGraph
import com.jmabilon.mymovietheater.presentation.theme.MyMovieTheaterTheme
import com.jmabilon.mymovietheater.presentation.ui.onboarding.OnBoardingScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private lateinit var viewModel: OnBoardingScreenViewModel
    private var showOnBoarding: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[OnBoardingScreenViewModel::class.java]
        initViewModelObservations()
        setContent {
            MyMovieTheaterTheme {
                navController = rememberNavController()
                val startDestination = when (showOnBoarding) {
                    true -> ScreensRoute.OnBoardingScreen
                    false -> ScreensRoute.HomeGraph
                }
                SetupNavGraph(navHostController = navController, startDestination)
            }
        }
    }

    private fun initViewModelObservations() {
        viewModel.uiState.flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
            .onEach {
                showOnBoarding = it.showOnBoarding
            }.launchIn(lifecycleScope)
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyMovieTheaterTheme {
        // do nothing
    }
}