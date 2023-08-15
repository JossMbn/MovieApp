package com.jmabilon.mymovietheater.network.api

import com.jmabilon.mymovietheater.data.datasource.remote.reponse.TrendingDTO
import retrofit2.Response
import retrofit2.http.GET

interface PopularAPI {

    @GET("movie/top_rated")
    suspend fun getPopularMovies(
    ) : Response<TrendingDTO?>

    @GET("tv/top_rated")
    suspend fun getPopularTvShow(
    ) : Response<TrendingDTO?>
}