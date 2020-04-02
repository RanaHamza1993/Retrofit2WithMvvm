package com.hamza.retrofit2withmvvm.enpoints

import com.hamza.retrofit2withmvvm.models.Destination
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface DestinationService {
    @GET("destination")
   suspend fun getDestinations(): Response<List<Destination>>

    @GET("destination")
    suspend fun getDestinations(@QueryMap filter:HashMap<String,String>?): Response<List<Destination>>
    @GET("destination/{id}")
    suspend fun getDestination(@Path("id")id:Int):Response<Destination>

    @POST("destination")
    suspend fun addDestination(@Body destination: Destination):Response<Destination>
    @PUT("destination/{id}")
    suspend fun updateDestination(@Path("id")id:Int,@Body destination: Destination):Response<Destination>
    @DELETE("destination/{id}")
    suspend fun deleteDestination(@Path("id")id:Int):Response<String>

}