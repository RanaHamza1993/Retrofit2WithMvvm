package com.hamza.retrofit2withmvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hamza.retrofit2withmvvm.generics.Coroutines
import com.hamza.retrofit2withmvvm.models.Destination
import com.hamza.retrofit2withmvvm.repos.RepositoryClass
import kotlinx.coroutines.Job

class DestinationListActivityViewModel: ViewModel() {
    lateinit var job: Job
    var destinationList=MutableLiveData<List<Destination>>()
    lateinit var repo: RepositoryClass
    fun init(repo:RepositoryClass){
        this.repo=repo

    }
    fun getDestinationsList():LiveData<List<Destination>>{
        job=Coroutines.ioThenMain({
            repo.getDestinationsList()
        },{
            destinationList.value=it
        })

        return destinationList
    }

    override fun onCleared() {
        if(::job.isInitialized){
            job.cancel()
        }
        super.onCleared()

    }

    //    private lateinit var destinationList:MutableLiveData<List<Destination>>
//    lateinit var repo: RepositoryClass
//    fun init(repo:RepositoryClass){
//        this.repo=repo
//
//    }
//    fun getDestinationsList():LiveData<List<Destination>>{
//
//        destinationList=repo.getOldDestinationsList()
//        return destinationList
//    }


}