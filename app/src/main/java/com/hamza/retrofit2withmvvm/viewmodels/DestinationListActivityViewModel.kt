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
    lateinit var repo: RepositoryClass
    fun init(repo:RepositoryClass){
        this.repo=repo

    }
    fun getDestinationsList():LiveData<List<Destination>>{
        destinationList=repo.getDestinationsList()
        return destinationList
    }

}