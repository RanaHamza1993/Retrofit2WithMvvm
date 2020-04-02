package com.hamza.retrofit2withmvvm.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hamza.retrofit2withmvvm.enpoints.DestinationService
import com.hamza.retrofit2withmvvm.generics.Couroutines
import com.hamza.retrofit2withmvvm.models.Destination
import com.hamza.retrofit2withmvvm.repos.RepositoryClass
import retrofit2.Retrofit

class DestinationListActivityViewModel: ViewModel() {
    private lateinit var destinationList:MutableLiveData<List<Destination>>
    fun init(context: Context, repo:RepositoryClass){

            destinationList=repo.getDestinationsList()


    }
    fun getDestinationsList():LiveData<List<Destination>>{
        return destinationList
    }

}