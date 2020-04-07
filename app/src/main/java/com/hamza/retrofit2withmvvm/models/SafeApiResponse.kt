package com.hamza.retrofit2withmvvm.models

sealed class ResultWrapper<out T> {
    data class SafeApiResponse<T>(
        val list: T?,
        val error: String?,
        val statusCode: Int?
    )
}