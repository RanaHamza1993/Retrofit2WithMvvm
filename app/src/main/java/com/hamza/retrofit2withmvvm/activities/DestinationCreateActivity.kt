package com.hamza.retrofit2withmvvm.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hamza.retrofit2withmvvm.R
import com.hamza.retrofit2withmvvm.models.Destination
import com.hamza.retrofit2withmvvm.utils.SampleData
import kotlinx.android.synthetic.main.activity_destiny_create.*

class DestinationCreateActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_destiny_create)

		setSupportActionBar(toolbar)
		val context = this

		// Show the Up button in the action bar.
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		btn_add.setOnClickListener {
			val newDestination = Destination()
			newDestination.city = et_city.text.toString()
			newDestination.description = et_description.text.toString()
			newDestination.country = et_country.text.toString()

			// To be replaced by retrofit code
			SampleData.addDestination(newDestination)
            finish() // Move back to DestinationListActivity
		}
	}
}
