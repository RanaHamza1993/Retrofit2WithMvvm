package com.hamza.retrofit2withmvvm.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.hamza.retrofit2withmvvm.R
import com.hamza.retrofit2withmvvm.adapters.DestinationAdapter
import com.hamza.retrofit2withmvvm.enpoints.DestinationService
import com.hamza.retrofit2withmvvm.models.Destination
import com.hamza.retrofit2withmvvm.repos.RepositoryClass
import com.hamza.retrofit2withmvvm.utils.SampleData
import com.hamza.retrofit2withmvvm.utils.ServiceBuilder
import com.hamza.retrofit2withmvvm.viewmodels.DestinationListActivityViewModel
import kotlinx.android.synthetic.main.activity_destiny_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DestinationListActivity : AppCompatActivity() {

    val viewModel:DestinationListActivityViewModel by lazy {

        ViewModelProvider(this).get(DestinationListActivityViewModel::class.java)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destiny_list)
        viewModel.init(this.applicationContext, RepositoryClass((this.applicationContext),ServiceBuilder(DestinationService::class.java)))
        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener {
            val intent = Intent(this@DestinationListActivity, DestinationCreateActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        loadDestinations()
    }

    private fun loadDestinations() {
        viewModel.getDestinationsList().observe(this, Observer {destinationsList->
            destiny_recycler_view.adapter = DestinationAdapter(destinationsList)

        })

        // To be replaced by retrofit code
        //destiny_recycler_view.adapter = DestinationAdapter(SampleData.DESTINATIONS)
    }
}
