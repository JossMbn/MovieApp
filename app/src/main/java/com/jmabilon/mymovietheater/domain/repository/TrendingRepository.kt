package com.jmabilon.mymovietheater.domain.repository

import com.jmabilon.mymovietheater.domain.model.Trending
import com.jmabilon.mymovietheater.network.Resource
import kotlinx.coroutines.flow.Flow

interface TrendingRepository {

    suspend fun getTrending() : Flow<Resource<List<Trending>?>>
}