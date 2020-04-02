package com.hamza.retrofit2withmvvm.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val URL="http://10.0.2.2:9000/"

    //create OKhttp Client
    private  val okhttpBuilder=OkHttpClient.Builder()

    //Create Retrofit Builder

    private val builder=Retrofit.Builder().
                        baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
                        .client(okhttpBuilder.build())
}