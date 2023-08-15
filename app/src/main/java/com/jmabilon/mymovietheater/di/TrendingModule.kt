package com.jmabilon.mymovietheater.di

import com.jmabilon.mymovietheater.data.datasource.remote.reponse.TrendingResultsDTO
import com.jmabilon.mymovietheater.data.mapper.DomainMapper
import com.jmabilon.mymovietheater.data.mapper.TrendingMapper
import com.jmabilon.mymovietheater.data.repository.TrendingRepositoryImpl
import com.jmabilon.mymovietheater.domain.model.Trending
import com.jmabilon.mymovietheater.domain.repository.TrendingRepository
import com.jmabilon.mymovietheater.domain.usecase.GetTrendingUseCase
import com.jmabilon.mymovietheater.network.api.TrendingAPI
import com.jmabilon.mymovietheater.network.error.ErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrendingModule {

    /////////////////////////////////////////////////////////////////////
    //////  USE CASE
    /////////////////////////////////////////////////////////////////////

    @Singleton
    @Provides
    fun provideTrendingUseCase(
        trendingRepository: TrendingRepository
    ): GetTrendingUseCase {
        return GetTrendingUseCase(trendingRepository)
    }

    /////////////////////////////////////////////////////////////////////
    //////  REPOSITORY
    /////////////////////////////////////////////////////////////////////

    @Singleton
    @Provides
    fun provideTrendingRepository(
        trendingAPI: TrendingAPI,
        domainMapper: DomainMapper<TrendingResultsDTO, Trending>,
        errorHandle: ErrorHandler
    ): TrendingRepository {
        return TrendingRepositoryImpl(trendingAPI, domainMapper, errorHandle)
    }

    /////////////////////////////////////////////////////////////////////
    //////  API
    /////////////////////////////////////////////////////////////////////

    @Singleton
    @Provides
    fun provideTrendingApi(retrofit: Retrofit): TrendingAPI {
        return retrofit.create(TrendingAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideDomainMapper(): DomainMapper<TrendingResultsDTO, Trending> {
        return TrendingMapper()
    }
}