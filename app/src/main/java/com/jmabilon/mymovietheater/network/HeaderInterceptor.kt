package com.jmabilon.mymovietheater.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor() : Interceptor {

    companion object {
        private const val HEADER_AUTHORIZATION_KEY = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3YmEwNDEzNWFmZTJmZGIyMzRlZmY1YzZlZTNmZjRhNCIsInN1YiI6IjYxZjdhMGIwMDcyMTY2MDA0MzE3Mjc1YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.3KrvOFys5kmDtYhsKwuNSqkp6PZfaqogcB3D1YGPKZE"
        private const val HEADER_ACCEPT_KEY = "application/json"
    }

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request().newBuilder()
                .addHeader("Authorization", HEADER_AUTHORIZATION_KEY)
                .addHeader("accept", HEADER_ACCEPT_KEY)
                .build()
        )
    }
}