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
import kotlinx.android.synthetic.main.fragment_appointment.*
import java.lang.StringBuilder
import java.util.*
import android.app.AlertDialog
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
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
        //RecyclerView = findViewById(R.id.profile_rv)
        val db = FirebaseFirestore.getInstance()
    }
    fun addData(view: View){
        val appointment = db.collection("Make_Appointment")
        val appointmentInfo  = AppointmentHistory(
               appt_date.text.toString(),
               appt_type.text.toString(),
                appt_vehicle.text.toString(),
              appt_details.text.toString()
            /*
             "DATE" to appt_date.text.toString(),
                "Type" to appt_type.text.toString(),
                "Vehicle" to appt_vehicle.text.toString(),
                "Details" to appt_details.text.toString()
            * */
        )
        val id =appointment.document().id
        appointment.document(id).set(appointmentInfo)
        clearValues()
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
                    showDialog("Data Listing", buffer.toString())
                }
                .addOnFailureListener {
                    Log.d(TAG, "Error getting documents")
                    showDialog("Error", "Error getting documents")
                }
    }
    fun RealtimeupdateData(view: View){

        // Get real time update
        db.collection("Make_Appointment")
                .addSnapshotListener{ snapshots, e ->
                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e)
                        return@addSnapshotListener
                    }

                    if (snapshots != null) {
                        // This will be called every time a document is updated
                        Log.d(TAG, "onEvent: -----------------------------")

                        val date_String = StringBuilder()
                        val type_String = StringBuilder()
                        val vehicle_String = StringBuilder()
                        val details_String = StringBuilder()
                        // Convert documents to a collection of Contact
                        val contacts = snapshots.toObjects<AppointmentHistory>()

                        for (contact in contacts) {
                            Log.d(TAG, "Current data: $contact")
                            date_String.append("Date : ${contact.date}")
                            type_String.append("Type: ${contact.type}")
                            vehicle_String.append("Vehicle: ${contact.vehicle}")
                            details_String.append("Details: ${contact.details}")
                            //showData(contact)
                        }
                        // Update the textview
                        appt_type.text = type_String.toString()
                        appt_date.text = date_String.toString()
                        appt_vehicle.text = vehicle_String.toString()
                        appt_details.text = details_String.toString()

                    } else {
                        Log.d(TAG, "Current data: null")
                    }
                }

    }
    private fun showData(contact: AppointmentHistory){
        appt_date.setText(contact.date.toString())
        appt_type.setText(contact.type.toString())
        appt_vehicle.setText(contact.vehicle.toString())
        appt_details.setText(contact.details.toString())
    }
    private fun showToast(text: String){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
    private fun showDialog(title : String,Message : String){
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }
    fun clearValues(){
        model_text.text.clear()
        details_text.text.clear()
        make_text.text.clear()
        year_text.text.clear()
        maintenance_box.setChecked(false)
        repair_box.setChecked(false)
        other_box.setChecked(false)
    }
}