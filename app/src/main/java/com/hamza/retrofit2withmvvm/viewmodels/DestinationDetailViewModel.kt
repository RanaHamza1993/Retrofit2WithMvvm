package com.hamza.retrofit2withmvvm.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hamza.retrofit2withmvvm.enpoints.DestinationService
import com.hamza.retrofit2withmvvm.models.Destination
import com.hamza.retrofit2withmvvm.repos.RepositoryClass

class DestinationDetailViewModel: ViewModel() {
    lateinit var destination: MutableLiveData<Destination>
    lateinit var deleteDestination: MutableLiveData<String>
    lateinit var repo: RepositoryClass
    fun init(repo: RepositoryClass){
        this.repo= repo
    }

    fun getDestinationDetail(id:Int):LiveData<Destination>{
        destination=repo.getDestinationDetail(id)
        return destination
    }
    fun updateDestinationDetail(id:Int,destinationObj: Destination):LiveData<Destination>{
        destination=repo.updateDestinationDetail(id,destinationObj)
        return destination
    }
    fun deleteDestinationDetail(id:Int):LiveData<String>{
        deleteDestination=repo.deleteDestinationDetail(id)
        return deleteDestination
    }
}