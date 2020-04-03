package com.hamza.retrofit2withmvvm.repos

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.hamza.retrofit2withmvvm.enpoints.DestinationService
import com.hamza.retrofit2withmvvm.generics.Coroutines
import com.hamza.retrofit2withmvvm.generics.SafeApiRequest
import com.hamza.retrofit2withmvvm.models.Destination

class RepositoryClass(
    private val context: Context,
    private val destinationService: DestinationService
):SafeApiRequest() {
    private val destinationList = MutableLiveData<List<Destination>>()
    private val destination=MutableLiveData<Destination>()
    private val deleteDestination=MutableLiveData<String>()

    //private val createdDestination=MutableLiveData<Destination>()

    companion object {
        @Volatile
        private var INSTANCE: RepositoryClass? = null

        fun getInstance(context: Context, destinationService: DestinationService) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: RepositoryClass(context, destinationService).also {
                    INSTANCE = it
                }
            }
    }

    //   fun getDestinationsList():MutableLiveData<List<Destination>>{
//        destinationService.getDestinations().enqueue(object : Callback<List<Destination>> {
//            override fun onFailure(call: Call<List<Destination>>, t: Throwable) {
//                Toast.makeText(context,t.message,Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onResponse(
//                call: Call<List<Destination>>,
//                response: Response<List<Destination>>
//            ) {
//                if(response.isSuccessful)
//                destinationList.postValue(response.body()!!)
//            }
//
//        })
//        return destinationList
//    }
     fun getOldDestinationsList(): MutableLiveData<List<Destination>> {
        Coroutines.main{
            val list = apiRequest{destinationService.getDestinations()}
            destinationList.postValue(list)
        }


        return destinationList
    }
    suspend fun getDestinationsList(): List<Destination> {
        return apiRequest{destinationService.getDestinations()}
    }
    fun getDestinationsList(query:HashMap<String,String>?): MutableLiveData<List<Destination>> {
        Coroutines.main{
            val list = apiRequest{destinationService.getDestinations(query)}
            destinationList.postValue(list)
        }


        return destinationList
    }
    fun getDestinationDetail(id:Int):MutableLiveData<Destination>{
        Coroutines.main{
            destination.value=apiRequest{destinationService.getDestination(id)}
        }
        return destination
    }
    fun addDestination(destinationObj: Destination):MutableLiveData<Destination>{
        Coroutines.main{
             destination.value=apiRequest{destinationService.addDestination(destinationObj)}
        }
        return destination
    }
    fun updateDestinationDetail(id: Int,destinationObj: Destination):MutableLiveData<Destination>{
        Coroutines.main{
            destination.value=apiRequest{destinationService.updateDestination(id,destinationObj)}
        }
        return destination
    }
    fun deleteDestinationDetail(id: Int):MutableLiveData<String>{
        Coroutines.main{
            deleteDestination.value=apiRequest{destinationService.deleteDestination(id)}
        }
        return deleteDestination
    }
}