package com.jmabilon.mymovietheater.network.api

import com.jmabilon.mymovietheater.data.datasource.remote.reponse.TrendingDTO
import retrofit2.Response
import retrofit2.http.GET

interface TrendingAPI {

    @GET("trending/all/week")
    suspend fun getTrending(
    ) : Response<TrendingDTO?>
}