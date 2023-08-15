package com.jmabilon.mymovietheater.presentation.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmabilon.mymovietheater.domain.model.Trending
import com.jmabilon.mymovietheater.domain.usecase.GetPopularMovieUseCase
import com.jmabilon.mymovietheater.domain.usecase.GetPopularTvShowUseCase
import com.jmabilon.mymovietheater.domain.usecase.GetTrendingUseCase
import com.jmabilon.mymovietheater.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getTrendingUseCase: GetTrendingUseCase,
    private val getPopularMovieUseCase: GetPopularMovieUseCase,
    private val getPopularTvShowUseCase: GetPopularTvShowUseCase
) : ViewModel(), HomeScreenContract.ViewModel {

    private val _uiState = MutableStateFlow(value = HomeScreenUiState())

    override val uiState: StateFlow<HomeScreenUiState>
        get() = _uiState

    init {
        getData()
    }

    override fun getData() {
        updateLoading(true)
        fetchTrending()
        fetchPopularMovie()
        fetchPopularTvShow()
        updateLoading(false)
    }

    private fun fetchTrending() {
        viewModelScope.launch {
            getTrendingUseCase()
                .catch { error ->
                    Log.d("callAPI", "getTrendingUseCase : ${error.message}")
                }
                .collect { response ->
                    when (response) {
                        is Resource.Error -> {
                            response.message?.let {
                                updateError(it)
                            }
                        }

                        is Resource.Success -> {
                            updateTrendingList(response.data)
                        }
                    }
                }
        }
    }

    private fun fetchPopularMovie() {
        viewModelScope.launch {
            getPopularMovieUseCase()
                .catch { error ->
                    Log.d("callAPI", "getPopularMovieUseCase : ${error.message}")
                }
                .collect { response ->
                    when (response) {
                        is Resource.Error -> {
                            response.message?.let {
                                updateError(it)
                            }
                        }

                        is Resource.Success -> {
                            updatePopularMovieList(response.data)
                        }
                    }
                }
        }
    }

    private fun fetchPopularTvShow() {
        viewModelScope.launch {
            getPopularTvShowUseCase()
                .catch { error ->
                    Log.d("callAPI", "getPopularTvShowUseCase : ${error.message}")
                }
                .collect { response ->
                    when (response) {
                        is Resource.Error -> {
                            response.message?.let {
                                updateError(it)
                            }
                        }

                        is Resource.Success -> {
                            updatePopularTvShowList(response.data)
                        }
                    }
                }
        }
    }

    private fun updateTrendingList(list: List<Trending>?) {
        _uiState.update { currentState ->
            currentState.copy(
                trendingList = list,
                error = null
            )
        }
    }

    private fun updatePopularMovieList(list: List<Trending>?) {
        _uiState.update { currentState ->
            currentState.copy(
                popularMovieList = list,
                error = null
            )
        }
    }

    private fun updatePopularTvShowList(list: List<Trending>?) {
        _uiState.update { currentState ->
            currentState.copy(
                popularTvShowList = list,
                error = null
            )
        }
    }

    private fun updateLoading(isLoading: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                isLoading = isLoading
            )
        }
    }

    private fun updateError(error: String) {
        _uiState.update { currentState ->
            currentState.copy(
                error = error
            )
        }
    }
}