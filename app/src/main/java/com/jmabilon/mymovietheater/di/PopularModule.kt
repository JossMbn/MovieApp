package com.jmabilon.mymovietheater.di

import com.jmabilon.mymovietheater.data.datasource.remote.reponse.TrendingResultsDTO
import com.jmabilon.mymovietheater.data.mapper.DomainMapper
import com.jmabilon.mymovietheater.data.mapper.TrendingMapper
import com.jmabilon.mymovietheater.data.repository.PopularRepositoryImpl
import com.jmabilon.mymovietheater.data.repository.TrendingRepositoryImpl
import com.jmabilon.mymovietheater.domain.model.Trending
import com.jmabilon.mymovietheater.domain.repository.PopularRepository
import com.jmabilon.mymovietheater.domain.repository.TrendingRepository
import com.jmabilon.mymovietheater.domain.usecase.GetPopularMovieUseCase
import com.jmabilon.mymovietheater.domain.usecase.GetPopularTvShowUseCase
import com.jmabilon.mymovietheater.domain.usecase.GetTrendingUseCase
import com.jmabilon.mymovietheater.network.api.PopularAPI
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
object PopularModule {

    /////////////////////////////////////////////////////////////////////
    //////  USE CASE
    /////////////////////////////////////////////////////////////////////

    @Singleton
    @Provides
    fun providePopularMoviesUseCase(
        popularRepository: PopularRepository
    ): GetPopularMovieUseCase {
        return GetPopularMovieUseCase(popularRepository)
    }

    @Singleton
    @Provides
    fun providePopularTvShowUseCase(
        popularRepository: PopularRepository
    ): GetPopularTvShowUseCase {
        return GetPopularTvShowUseCase(popularRepository)
    }

    /////////////////////////////////////////////////////////////////////
    //////  REPOSITORY
    /////////////////////////////////////////////////////////////////////

    @Singleton
    @Provides
    fun providePopularRepository(
        popularAPI: PopularAPI,
        domainMapper: DomainMapper<TrendingResultsDTO, Trending>,
        errorHandler: ErrorHandler
    ): PopularRepository {
        return PopularRepositoryImpl(popularAPI, domainMapper, errorHandler)
    }

    @Singleton
    @Provides
    fun providePopularApi(retrofit: Retrofit): PopularAPI {
        return retrofit.create(PopularAPI::class.java)
    }
}