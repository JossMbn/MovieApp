package com.jmabilon.mymovietheater.data.repository

import com.jmabilon.mymovietheater.data.datasource.remote.reponse.TrendingResultsDTO
import com.jmabilon.mymovietheater.data.mapper.DomainMapper
import com.jmabilon.mymovietheater.domain.model.Trending
import com.jmabilon.mymovietheater.domain.repository.TrendingRepository
import com.jmabilon.mymovietheater.network.Resource
import com.jmabilon.mymovietheater.network.api.TrendingAPI
import com.jmabilon.mymovietheater.network.error.ErrorHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TrendingRepositoryImpl @Inject constructor(
    private val trendingAPI: TrendingAPI,
    private val domainMapper: DomainMapper<TrendingResultsDTO, Trending>,
    private val errorHandler: ErrorHandler
) : TrendingRepository {

    override suspend fun getTrending(): Flow<Resource<List<Trending>?>> = flow {
        try {
            val result = trendingAPI.getTrending()
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