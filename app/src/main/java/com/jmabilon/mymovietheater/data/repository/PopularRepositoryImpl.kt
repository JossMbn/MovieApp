package com.jmabilon.mymovietheater.data.repository

import com.jmabilon.mymovietheater.data.datasource.remote.reponse.TrendingResultsDTO
import com.jmabilon.mymovietheater.data.mapper.DomainMapper
import com.jmabilon.mymovietheater.domain.model.Trending
import com.jmabilon.mymovietheater.domain.repository.PopularRepository
import com.jmabilon.mymovietheater.network.Resource
import com.jmabilon.mymovietheater.network.api.PopularAPI
import com.jmabilon.mymovietheater.network.error.ErrorHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PopularRepositoryImpl @Inject constructor(
    private val popularAPI: PopularAPI,
    private val domainMapper: DomainMapper<TrendingResultsDTO, Trending>,
    private val errorHandler: ErrorHandler
) : PopularRepository {

    override suspend fun getPopularMovies(): Flow<Resource<List<Trending>?>> = flow {
        try {
            val result = popularAPI.getPopularMovies()
            if (result.isSuccessful) {
                emit(
                    Resource.Success(
                        result.body()?.results?.map {
                            domainMapper.toDomain(it)
                        }
                    )
                )
            } else {
                emit(errorHandler.getErrorFromCode(result.code()))
            }
        } catch (e: Throwable) {
            emit(errorHandler.getError(e))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getPopularTvShow(): Flow<Resource<List<Trending>?>> = flow {
        try {
            val result = popularAPI.getPopularTvShow()
            if (result.isSuccessful) {
                emit(
                    Resource.Success(
                        result.body()?.results?.map {
                            domainMapper.toDomain(it)
                        }
                    )
                )
            } else {
                emit(errorHandler.getErrorFromCode(result.code()))
            }
        } catch (e: Throwable) {
            emit(errorHandler.getError(e))
        }
    }.flowOn(Dispatchers.IO)
}