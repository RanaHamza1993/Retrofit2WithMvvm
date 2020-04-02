package com.hamza.retrofit2withmvvm.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hamza.retrofit2withmvvm.R
import com.hamza.retrofit2withmvvm.enpoints.DestinationService
import com.hamza.retrofit2withmvvm.models.Destination
import com.hamza.retrofit2withmvvm.repos.RepositoryClass
import com.hamza.retrofit2withmvvm.utils.SampleData
import com.hamza.retrofit2withmvvm.utils.ServiceBuilder
import com.hamza.retrofit2withmvvm.viewmodels.CreateDestinationViewModel
import kotlinx.android.synthetic.main.activity_destiny_create.*

class DestinationCreateActivity : AppCompatActivity() {

	val viewModel:CreateDestinationViewModel by lazy {
		ViewModelProvider(this).get(CreateDestinationViewModel::class.java)
	}
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_destiny_create)
		setSupportActionBar(toolbar)
		viewModel.init(RepositoryClass(this.applicationContext,ServiceBuilder(DestinationService::class.java)))
		val context = this
		// Show the Up button in the action bar.
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		btn_add.setOnClickListener {
			val newDestination = Destination()
			newDestination.city = et_city.text.toString()
			newDestination.description = et_description.text.toString()
			newDestination.country = et_country.text.toString()
			viewModel.getAddedDestination(newDestination)?.observe(this, Observer {
				finish()
			})
			// To be replaced by retrofit code
				//SampleData.addDestination(newDestination)
           // Move back to DestinationListActivity
			//finish()
		}
	}
}
