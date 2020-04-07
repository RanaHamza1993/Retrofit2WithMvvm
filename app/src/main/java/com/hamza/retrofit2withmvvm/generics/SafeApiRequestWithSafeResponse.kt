package com.hamza.retrofit2withmvvm.generics

import com.hamza.retrofit2withmvvm.models.ResultWrapper
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.lang.StringBuilder

abstract class SafeApiRequestWithSafeResponse<T> {
    suspend fun<T:Any> apiSafeRequest(call: suspend() -> Response<T>): ResultWrapper.SafeApiResponse<T>{
        var response: Response<T>?=null
        try {
            response = call.invoke()
        }catch (e:Exception){
            return ResultWrapper.SafeApiResponse(list = null,statusCode = response?.code(),error = e.toString())
        }
        if(response.isSuccessful){
            val responseBody: T? =response.body()
            if(responseBody!=null)
                return ResultWrapper.SafeApiResponse(list=response.body()!!,statusCode = response.code(),error = "")
            else
            // return "" as T
                return ResultWrapper.SafeApiResponse(null,statusCode = response.code(),error = "")
        }
        else{
            val errorr=response?.errorBody()?.toString()
            val message= StringBuilder()
            errorr?.let {
                try{
                    message.append(JSONObject(it).getString("message"))
                }catch (e: JSONException){
                    message.append("\n")
                }
                message.append("Error Code "+response?.code())
            }
            // throw ApiException(message.toString())
            return ResultWrapper.SafeApiResponse(list = null,statusCode = response?.code(),error = errorr)
        }

    }
}