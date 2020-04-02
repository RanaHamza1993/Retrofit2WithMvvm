package com.hamza.retrofit2withmvvm.generics
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.lang.StringBuilder

abstract class SafeApiRequest {

    suspend fun<T:Any> apiRequest(call: suspend() ->Response<T>):T{
       var response:Response<T>?=null
        try {
            response = call.invoke()
        }catch (e:Exception){
            println(e.toString())
        }
        if(response!=null&&response.isSuccessful)
            return response.body()!!
        else{
            val error=response?.errorBody()?.toString()
            val message=StringBuilder()
            error?.let {
                try{
                    message.append(JSONObject(it).getString("message"))
                }catch (e:JSONException){
                    message.append("\n")
                }
                message.append("Error Code "+response?.code())
            }
            throw ApiException(message.toString())
        }

    }
}