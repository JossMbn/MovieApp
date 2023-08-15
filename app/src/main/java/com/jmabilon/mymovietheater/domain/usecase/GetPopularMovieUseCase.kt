package com.jmabilon.mymovietheater.domain.usecase

import com.jmabilon.mymovietheater.domain.model.Trending
import com.jmabilon.mymovietheater.domain.repository.PopularRepository
import com.jmabilon.mymovietheater.domain.repository.TrendingRepository
import com.jmabilon.mymovietheater.network.Resource
import kotlinx.coroutines.flow.Flow

class GetPopularMovieUseCase(
    private val popularRepository: PopularRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Trending>?>> = popularRepository.getPopularMovies()
}