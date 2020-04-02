package com.hamza.retrofit2withmvvm.enpoints

import com.hamza.retrofit2withmvvm.models.Destination
import retrofit2.Call
import retrofit2.http.GET

interface DestinationService {
    @GET("destination")
    fun getDestinations(): Call<List<Destination>>
}