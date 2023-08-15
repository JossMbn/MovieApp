package com.jmabilon.mymovietheater.presentation.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmabilon.mymovietheater.helper.PrefHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingScreenViewModel @Inject constructor(
    private val prefHelper: PrefHelper
) : ViewModel(), OnBoardingScreenContract.ViewModel {

    private val _uiState = MutableStateFlow(value = OnBoardingScreenUiState())

    override val uiState: StateFlow<OnBoardingScreenUiState>
        get() = _uiState

    init {
        getShowOnBoardingVisibility()
    }

    override fun getShowOnBoardingVisibility() {
        viewModelScope.launch(Dispatchers.IO) {
            prefHelper.getOnBoardingVisibility()
                .distinctUntilChanged()
                .collect {
                    updateShowOnBoarding(it)
                }
        }
    }

    override fun setShowOnBoardingVisibility() {
        viewModelScope.launch(Dispatchers.IO) {
            prefHelper.setOnBoardingVisibility(false)
        }
    }

    private fun updateShowOnBoarding(showOnBoarding: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                showOnBoarding = showOnBoarding
            )
        }
    }
}