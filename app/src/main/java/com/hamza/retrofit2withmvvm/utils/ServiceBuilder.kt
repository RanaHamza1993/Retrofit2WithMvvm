package com.hamza.retrofit2withmvvm.utils

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val URL="http://10.0.2.2:9000/"

    //HttpInteceptor
    private val logger=HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    //create OKhttp Client
    private  val okhttpBuilder=OkHttpClient.Builder().addInterceptor(logger)



    //Create Retrofit Builder

    private val builder=Retrofit.Builder().
                        baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
                        .client(okhttpBuilder.build())

    private val retrofit= builder.build()

    operator fun<T> invoke(serviceType:Class<T>):T{
        return retrofit.create(serviceType)
    }
}