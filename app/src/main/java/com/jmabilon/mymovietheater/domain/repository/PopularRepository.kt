package com.jmabilon.mymovietheater.domain.repository

import com.jmabilon.mymovietheater.domain.model.Trending
import com.jmabilon.mymovietheater.network.Resource
import kotlinx.coroutines.flow.Flow

interface PopularRepository {

    suspend fun getPopularMovies() : Flow<Resource<List<Trending>?>>

    suspend fun getPopularTvShow() : Flow<Resource<List<Trending>?>>
}