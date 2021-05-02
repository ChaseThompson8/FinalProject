package com.example.finalproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import android.util.Log
import android.content.ContentValues.TAG
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item.*
//
import com.google.firebase.firestore.ktx.toObjects



class MainActivity : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()
    lateinit var RecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

//        supportActionBar?.hide()

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_appointment, R.id.navigation_location))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        ///
        RecyclerView = findViewById(R.id.profile_rv)
        populateList()
    }
    fun getData(){


    }
    fun addData(view: View){
        val appointment = db.collection("Make_Appointment")
        val appointmentInfo  = AppointmentHistory(
            appt_date.text.toString(),
            appt_type.text.toString(),
            appt_vehicle.text.toString(),
            appt_details.text.toString()

        )
        val id =appointment.document().id
        appointment.document(id).set(appointment)
    }
    fun displayData(view: View ){
        db.collection("Make_Appointment")
                .orderBy("Date")
                .get()
                .addOnSuccessListener { documents ->

                    val buffer = StringBuffer()

                    // Turn your document(s) to Contact object
                    val contacts = documents.toObjects<AppointmentHistory>()

                    for (contact in contacts) {

                        Log.d(TAG, "contact: ${contact}")

                        // Create a string buffer (i.e., concatenate all the fields into one string)
                        buffer.append("Date: ${contact.date}" + "\n")
                        buffer.append("Type : ${contact.type}" + "\n")
                        buffer.append("Vehicle :  ${contact.vehicle}" + "\n")
                        buffer.append("details :  ${contact.details}" + "\n\n")
                    }

                    // show all the records as a string in a dialog
                   // showDialog("Data Listing", buffer.toString())
                }
                .addOnFailureListener {
                    Log.d(TAG, "Error getting documents")
                    //showDialog("Error", "Error getting documents")
                }
    }
    fun populateList(){

    }
}