package com.jmabilon.mymovietheater.network.error

import com.jmabilon.mymovietheater.network.Resource
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

class ErrorHandlerImpl(): ErrorHandler {

    override fun <T> getError(throwable: Throwable): Resource<T> {
        return when (throwable) {
            is IOException -> Resource.Error(errorMessage = "Please check your network connection")
            is HttpException -> {
                getErrorFromCode(throwable.code())
            }
            else -> Resource.Error(errorMessage = "Something went wrong")
        }
    }

    override fun <T> getErrorFromCode(code: Int): Resource<T> {
        return when (code) {
            HttpURLConnection.HTTP_NOT_FOUND -> Resource.Error(errorMessage = "Not found")
            HttpURLConnection.HTTP_FORBIDDEN -> Resource.Error(errorMessage = "Access Denied")
            HttpURLConnection.HTTP_UNAVAILABLE -> Resource.Error(errorMessage = "The service is currently unavailable")
            HttpURLConnection.HTTP_UNAUTHORIZED -> Resource.Error(errorMessage = "Not Authorized")
            else -> Resource.Error(errorMessage = "Something went wrong")
        }
    }
}