package com.example.finalproject.ui.appointment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.finalproject.MainActivity
import com.example.finalproject.R
import kotlinx.android.synthetic.main.activity_confirmation.*

class ConfirmationActivity : AppCompatActivity() {

    private val REQUEST_CODE = 88

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        val makeInfo = intent.getStringExtra("Make")
        val modelInfo = intent.getStringExtra("Model")
        val yearInfo = intent.getStringExtra("Year")
        val dateInfo = intent.getStringExtra("Date")

        confirmation_body.text = "You appointment for your $yearInfo $makeInfo $modelInfo is all set for $dateInfo."

        new_appt_button.setOnClickListener {

            val myIntent = Intent(this, MainActivity::class.java)
            startActivityForResult(myIntent, REQUEST_CODE)

//            supportFragmentManager.beginTransaction()
//                .replace(R.id.nav_host_fragment, AppointmentFragment())
//                .addToBackStack(null)
//                .commit()
        }

    }

}