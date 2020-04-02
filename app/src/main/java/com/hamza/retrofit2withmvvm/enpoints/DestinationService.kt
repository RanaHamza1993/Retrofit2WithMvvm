package com.hamza.retrofit2withmvvm.enpoints

import com.hamza.retrofit2withmvvm.models.Destination
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface DestinationService {
    @GET("destination")
   suspend fun getDestinations(): Response<List<Destination>>
}