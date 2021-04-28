package com.example.finalproject.ui.appointment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject.R
import kotlinx.android.synthetic.main.fragment_appointment.*

class AppointmentFragment : Fragment() {

    private lateinit var appointmentViewModel: AppointmentViewModel

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

        if (checkBox == null && checkBox2 == null && checkBox3 == null) {
            showDialogue("Check boxes not indicated", "Check off what kind of service appointment you'd like to schedule")
        }
        else if (make_text == null || model_text == null || year_text == null || details_text == null) {
            showDialogue("Car info or details for appointment not entered", "Please fill out the info for you vehicle and the details for the appointment")
        }
        else if (calander_item == null) {
            showDialogue("Date of Appointment not specified", "Please select the date you wish to schedule the appointment for")
        }

        val sharedPreferences = activity!!.getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        

        return root
    }

    private fun showDialogue(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setIcon(android.R.drawable.ic_delete)
        builder.setPositiveButton("OKAY"){ dialog, which ->
        }
        val dialog = builder.create()
        dialog.show()
    }
}