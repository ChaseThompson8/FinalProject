package com.example.finalproject.ui.appointment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.finalproject.R
import kotlinx.android.synthetic.main.fragment_confirmation.view.*

class ConfirmationFragment : Fragment() {

    val TAG = "ConfirmationFragment"
    var make = ""
    var model = ""
    var year = ""
    var date = ""

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_confirmation, container, false)

        val sharedPreference = activity?.getPreferences(Context.MODE_PRIVATE)
        val makeInfo = sharedPreference?.getString("Make", make) ?: ""
        val modelInfo = sharedPreference?.getString("Model", model) ?: ""
        val yearInfo = sharedPreference?.getString("Year", year) ?: ""
        val dateInfo = sharedPreference?.getString("Date", date) ?: ""


        root.confirmation_body.text = "You appointment for your $yearInfo $makeInfo $modelInfo is all set for $dateInfo."


        root.new_appt_button.setOnClickListener {
            val navController = Navigation.findNavController(root)
            navController.navigate(R.id.navigation_appointment)
        }

        root.home_button.setOnClickListener {
            val navController = Navigation.findNavController(root)
            navController.navigate(R.id.navigation_home)
        }

        return root
    }

}
