package com.jmabilon.mymovietheater.presentation.ui.onboarding

import kotlinx.coroutines.flow.StateFlow

class OnBoardingScreenContract {

    interface ViewModel {
        val uiState: StateFlow<OnBoardingScreenUiState>

        fun getShowOnBoardingVisibility()
        fun setShowOnBoardingVisibility()
    }
}