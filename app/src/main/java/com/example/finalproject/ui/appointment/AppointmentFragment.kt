package com.example.finalproject.ui.appointment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.finalproject.R
import kotlinx.android.synthetic.main.fragment_appointment.*
import kotlinx.android.synthetic.main.fragment_appointment.view.*
import java.util.*

class AppointmentFragment : Fragment() {

    private lateinit var appointmentViewModel: AppointmentViewModel
    val TAG = "AppointmentFragment"
    private val REQUEST_CODE = 88

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        appointmentViewModel = ViewModelProvider(this).get(AppointmentViewModel::class.java)
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
            }
            else if (make.isEmpty() || model.isEmpty() || year.isEmpty() || details.isEmpty()) {
                Log.d(TAG, "Missing car info")
                showDialogue("Car info or details missing", "Please fill out the info " +
                        "for you vehicle and the details for the appointment")
            }
//            else if (newDate < plusDay) {
//                Log.d(TAG, "No date selected")
//                showDialogue("Date of Appointment not specified",
//                    "Please select the date you wish to schedule the appointment for.\n" +
//                            "Make sure the date selected is at least one day from the current date")
//            }
            else {

                editor.putString("Make", make)
                editor.putString("Model", model)
                editor.putString("Year", year)
                editor.putString("Details", details)
                editor.putString("Date", date)

                editor.apply()

                val navController = Navigation.findNavController(root)
                navController.navigate(R.id.confirmationFragment)

//                requireActivity().supportFragmentManager.beginTransaction()
//                    .replace(R.id.nav_host_fragment, ConfirmationFragment())
//                    .addToBackStack(null)
//                    .commit()

//                val myIntent = Intent(root.context, ConfirmationActivity::class.java)
//
//                myIntent.putExtra("Make", make)
//                myIntent.putExtra("Model", model)
//                myIntent.putExtra("Year", year)
//                myIntent.putExtra("Date", date)
//
//                startActivityForResult(myIntent, REQUEST_CODE)
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
}