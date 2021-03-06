package com.example.finalproject.ui.appointment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.motion.utils.Oscillator
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.finalproject.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_appointment.*
import kotlinx.android.synthetic.main.fragment_appointment.view.*
import android.media.MediaPlayer

class AppointmentFragment : Fragment() {

    val TAG = "AppointmentFragment"
    private val REQUEST_CODE = 88
    var myMediaPlayer : MediaPlayer? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_appointment, container, false)

        // Check to see if the user changes the date on the calendar, convert it into a string and store in a variable
        var date = ""
        root.calendar_item.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val month1 = month+1
            date = "$month1/$dayOfMonth/$year"
        }

        root.submit_button.setOnClickListener {
            val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            val make = make_text.text.toString()
            val model = model_text.text.toString()
            val year = year_text.text.toString()
            val details = details_text.text.toString()
            var maintenance = false
            var repair = false
            var other = false

            // Check to see what check boxes are clicked
            if (maintenance_box.isChecked) {
                maintenance = true
                editor.putBoolean("Maintenance", maintenance)
                Log.d(TAG, "Maintenance = $maintenance")
            }
            if (repair_box.isChecked) {
                repair = true
                editor.putBoolean("Repair", repair)
                Log.d(TAG, "Repair = $repair")
            }
            if (other_box.isChecked) {
                other = true
                editor.putBoolean("Other", other)
                Log.d(TAG, "Other = $other")
            }

            //Check to see if the user filled out all the necessary queries to schedule an appointment
            if (!maintenance && !repair && !other) {
                Log.d(TAG, "No check box selected")
                showDialogue("Check box not selected", "Check off what kind of service" +
                        " appointment you'd like to schedule")
                playSound(root)
            }
            else if (make.isEmpty() || model.isEmpty() || year.isEmpty() || details.isEmpty()) {
                Log.d(TAG, "Missing car info")
                showDialogue("Car info or details missing", "Please fill out the info " +
                        "for you vehicle and the details for the appointment")
            }
            else {
                val db = FirebaseFirestore.getInstance()
                //Update Calender Item

                val data=hashMapOf(
                        "DamageRepair" to repair_box.isChecked,
                        "Maintenance" to maintenance_box.isChecked,
                        "vehicleMake" to make_text.text.toString(),
                        "vehicleModel" to model_text.text.toString(),
                        "Other" to other_box.isChecked,
                        "vehicleDate" to date,
                        "vehicleDetails" to details_text.text.toString(),
                        "vehicleYear" to year_text.text.toString()
                )
                db.collection("Make_Appointment").add(data)
                        .addOnSuccessListener{ documentReference -> Log.d(Oscillator.TAG, "DocumentSnapshot written with ID:${documentReference.id}")
                        }
                        .addOnFailureListener{ e->
                            Log.w(Oscillator.TAG, "Error adding document", e)
                        }

                editor.putString("Make", make)
                editor.putString("Model", model)
                editor.putString("Year", year)
                editor.putString("Details", details)
                editor.putString("Date", date)

                editor.apply()

                val navController = Navigation.findNavController(root)
                navController.navigate(R.id.confirmationFragment)
            }

        }

        return root
    }

    private fun showDialogue(title: String, message: String) {
        val builder = activity?.let { AlertDialog.Builder(it) }
        builder?.setTitle(title)
        builder?.setMessage(message)
        builder?.setIcon(android.R.drawable.ic_delete)
        builder?.setPositiveButton("OKAY"){ dialog, which ->
        }
        val dialog = builder?.create()
        dialog?.show()
    }
    fun playSound(view: View) {
        if (myMediaPlayer == null){
            myMediaPlayer = MediaPlayer.create(activity, R.raw.ehooga)
        }
        myMediaPlayer?.start()
    }
}