package com.example.catapi.common

sealed class Resource<T>(val message: String? = null, val dataResult: T? = null) {
    class Success<T>(dataResult: T) : Resource<T>(dataResult = dataResult)
    class Error<T>(message: String? = null, dataResult: T? = null): Resource<T> (message, dataResult)
    class Loading<T>(dataResult: T? = null) : Resource<T>(dataResult = dataResult)
}