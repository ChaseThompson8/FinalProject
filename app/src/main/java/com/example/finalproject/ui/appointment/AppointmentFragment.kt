package com.example.finalproject.ui.appointment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject.R
import kotlinx.android.synthetic.main.fragment_appointment.*
import kotlinx.android.synthetic.main.fragment_appointment.view.*

class AppointmentFragment : Fragment() {

    private lateinit var appointmentViewModel: AppointmentViewModel
    val TAG = "AppointmentFragment"

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        appointmentViewModel =
                ViewModelProvider(this).get(AppointmentViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_appointment, container, false)
//        val textView: TextView = root.findViewById(R.id.text_appointment)
        appointmentViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })

        root.submit_button.setOnClickListener {

            if (maintenance_box == null && repair_box == null && other_box == null) {
                Log.d(TAG, "No check box selected")
//                showDialogue("Check box not selected", "Check off what kind of service appointment you'd like to schedule")
            }
            else if (make_text == null || model_text == null || year_text == null || details_text == null) {
                Log.d(TAG, "Missing car info")
//                showDialogue("Car info or details for appointment not entered", "Please fill out the info for you vehicle and the details for the appointment")
            }
            else if (calendar_item == null) {
                Log.d(TAG, "No date selected")
//                showDialogue("Date of Appointment not specified", "Please select the date you wish to schedule the appointment for")
            }

            val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            val make = make_text.toString()
            val model = model_text.toString()
            val year = year_text.toString()
            val details = details_text.toString()
            val calendar = calendar_item.toString()
            val maintenance = maintenance_box
            var repair = false
            var other = false


//            editor.putBoolean("Maintenance", maintenance)
            Log.d(TAG, "Maintenance = $maintenance")

            editor.putBoolean("Repair", repair)
            Log.d(TAG, "Repair = $repair")

            editor.putBoolean("Other", other)
            Log.d(TAG, "Other = $other")


            editor.putString("Make", make)
            editor.putString("Model", model)
            editor.putString("Year", year)
            editor.putString("Details", details)
            editor.putString("Calendar", calendar)
//            editor.putBoolean("Maintenance", maintenance)

            Log.d(TAG, calendar)
            editor.apply()
        }

        return root
    }

//    private fun showDialogue(title: String, message: String) {
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle(title)
//        builder.setMessage(message)
//        builder.setIcon(android.R.drawable.ic_delete)
//        builder.setPositiveButton("OKAY"){ dialog, which ->
//        }
//        val dialog = builder.create()
//        dialog.show()
//    }
}