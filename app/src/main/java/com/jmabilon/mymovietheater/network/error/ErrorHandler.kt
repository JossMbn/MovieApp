package com.jmabilon.mymovietheater.network.error

import com.jmabilon.mymovietheater.network.Resource

interface ErrorHandler {

    fun <T> getError(throwable: Throwable): Resource<T>

    fun <T> getErrorFromCode(code: Int): Resource<T>
}