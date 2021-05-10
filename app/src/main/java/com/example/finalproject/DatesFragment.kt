package com.example.finalproject

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_dates.*
import kotlinx.android.synthetic.main.fragment_dates.view.*

class DatesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_dates, container, false)

        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        root.save_dates_btn.setOnClickListener {

            val oilChange = oil_edit.text.toString()
            val battery = battery_edit.text.toString()
            val coolant = coolant_edit.text.toString()
            val brakes = brake_edit.text.toString()
            val transmission = transmission_edit.text.toString()
            val airFilter = air_edit.text.toString()

            if (oilChange.isNotEmpty()){
                editor.putString("Oil", oilChange)
            }
            if (battery.isNotEmpty()){
                editor.putString("Battery", battery)
            }
            if (coolant.isNotEmpty()){
                editor.putString("Coolant", coolant)
            }
            if (brakes.isNotEmpty()){
                editor.putString("Brakes", brakes)
            }
            if (transmission.isNotEmpty()){
                editor.putString("Transmission", transmission)
            }
            if (airFilter.isNotEmpty()){
                editor.putString("AirFilter", airFilter)
            }

            editor.apply()

            val navController = Navigation.findNavController(root)
            navController.navigate(R.id.navigation_profile)
        }

        return root
    }

}