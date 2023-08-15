package com.jmabilon.mymovietheater.presentation.ui.home

import kotlinx.coroutines.flow.StateFlow

class HomeScreenContract {

    interface ViewModel {
        val uiState: StateFlow<HomeScreenUiState>

        fun getData()
    }
}