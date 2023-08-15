package com.jmabilon.mymovietheater.domain.usecase

import com.jmabilon.mymovietheater.domain.model.Trending
import com.jmabilon.mymovietheater.domain.repository.TrendingRepository
import com.jmabilon.mymovietheater.network.Resource
import kotlinx.coroutines.flow.Flow

class GetTrendingUseCase(
    private val trendingRepository: TrendingRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Trending>?>> = trendingRepository.getTrending()
}