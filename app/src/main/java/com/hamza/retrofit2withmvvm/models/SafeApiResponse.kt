package com.hamza.retrofit2withmvvm.models

sealed class SafeApiResponse<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T? = null, statusCode: Int?) : SafeApiResponse<T>(data)
    class Loading<T>(data: T? = null) : SafeApiResponse<T>(data)
    class Error<T>(data: T? = null, statusCode: Int?, message: String?) :
        SafeApiResponse<T>(data, message)

}