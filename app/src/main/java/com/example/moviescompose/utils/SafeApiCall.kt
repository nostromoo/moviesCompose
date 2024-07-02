package com.example.moviescompose.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T> safeApiCall(
    apiCall: suspend () -> Response<T>
): NetworkResult<T> {
    return withContext(Dispatchers.IO) {
        try {
            val call = apiCall()
            if (call.isSuccessful) {
                NetworkResult.Success(call.body())
            } else {
                NetworkResult.Failure(
                    false,
                    call.code(),
                    call.errorBody()
                )
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {
                is HttpException -> {
                    NetworkResult.Failure(
                        false,
                        throwable.code(),
                        throwable.response()?.errorBody()
                    )
                }

                else -> {
                    NetworkResult.Failure(true, null, null)
                }
            }
        }
    }
}