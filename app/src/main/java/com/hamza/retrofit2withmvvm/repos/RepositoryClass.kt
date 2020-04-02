package com.hamza.retrofit2withmvvm.repos

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.hamza.retrofit2withmvvm.enpoints.DestinationService
import com.hamza.retrofit2withmvvm.generics.Couroutines
import com.hamza.retrofit2withmvvm.generics.SafeApiRequest
import com.hamza.retrofit2withmvvm.models.Destination
import kotlinx.coroutines.CoroutineScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryClass(
    private val context: Context,
    private val destinationService: DestinationService
):SafeApiRequest() {
    val destinationList = MutableLiveData<List<Destination>>()

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
     fun getDestinationsList(): MutableLiveData<List<Destination>> {
        Couroutines.main{
            val list = apiRequest{destinationService.getDestinations()}
            destinationList.postValue(list)
        }


        return destinationList
    }

}