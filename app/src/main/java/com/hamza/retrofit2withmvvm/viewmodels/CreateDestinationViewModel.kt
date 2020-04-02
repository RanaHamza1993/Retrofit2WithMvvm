package com.hamza.retrofit2withmvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hamza.retrofit2withmvvm.models.Destination
import com.hamza.retrofit2withmvvm.repos.RepositoryClass

class CreateDestinationViewModel:ViewModel() {
    private var createdDestination:MutableLiveData<Destination>?=null
    private lateinit var repo:RepositoryClass
    fun init(repo:RepositoryClass){
        this.repo=repo
    }
    fun getAddedDestination(destination: Destination):LiveData<Destination>?{
        createdDestination=repo.addDestination(destination)
        return createdDestination
    }

}