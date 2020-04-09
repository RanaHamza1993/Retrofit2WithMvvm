package com.hamza.retrofit2withmvvm.generics

import com.hamza.retrofit2withmvvm.models.SafeApiResponse
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.lang.StringBuilder

abstract class SafeApiRequestWithSafeResponse<T> {
    suspend fun <T : Any> apiSafeRequest(call: suspend () -> Response<T>): SafeApiResponse<T> {
        var response: Response<T>? = null
        try {
            response = call.invoke()
        } catch (e: Exception) {
            return SafeApiResponse.Error(
                data = null,
                statusCode = response?.code(),
                message = e.toString()
            )
        }
        if (response.isSuccessful) {
            val responseBody: T? = response.body()
            if (responseBody != null)
                return SafeApiResponse.Success(data = response.body()!!, statusCode = response.code())
            else
            // return "" as T
                return SafeApiResponse.Success("" as T, statusCode = response.code())
        } else {
            val errorr = response.errorBody()?.toString()
            val message = StringBuilder()
            errorr?.let {
                try {
                    message.append(JSONObject(it).getString("message"))
                } catch (e: JSONException) {
                    message.append("\n")
                }
                message.append("Error Code " + response.code())
            }
            // throw ApiException(message.toString())
            return SafeApiResponse.Error(data = null, statusCode = response.code(), message = errorr)
        }

    }
}