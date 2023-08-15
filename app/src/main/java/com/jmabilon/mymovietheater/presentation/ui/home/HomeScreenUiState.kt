package com.jmabilon.mymovietheater.presentation.ui.home

import com.jmabilon.mymovietheater.domain.model.Trending

data class HomeScreenUiState(
    val isLoading: Boolean = false,
    val trendingList: List<Trending>? = null,
    val popularMovieList: List<Trending>? = null,
    val popularTvShowList: List<Trending>? = null,
    val error: String? = null
)
