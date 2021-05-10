package com.example.finalproject.ui.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.AppointmentHistory
import com.example.finalproject.R
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment() {

    private val TAG = "ProfileFragment"
    private var newName = ""
    private var newEmail = ""
    private var newPic = ""
    private var oilChange = ""
    private var battery = ""
    private var coolant = ""
    private var brakes = ""
    private var transmission = ""
    private var airFilter = ""


    lateinit var appointmentAdapter: ProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        val sharedPreference = activity?.getPreferences(Context.MODE_PRIVATE)
        val name = sharedPreference?.getString("Name", newName) ?: ""
        val email = sharedPreference?.getString("Email", newEmail) ?: ""
        val pic = sharedPreference?.getString("Pic", newPic) ?: ""
        val oil = sharedPreference?.getString("Oil", oilChange) ?: ""
        val battery = sharedPreference?.getString("Battery", battery) ?: ""
        val coolant = sharedPreference?.getString("Coolant", coolant) ?: ""
        val brakes = sharedPreference?.getString("Brakes", brakes) ?: ""
        val transmission = sharedPreference?.getString("Transmission", transmission) ?: ""
        val airFilter = sharedPreference?.getString("AirFilter", airFilter) ?: ""


        root.name.text = name
        root.email.text = email
        root.profile_img.setImageURI(pic.toUri())
        root.oil_text.text = "Oil Change: $oil"
        root.battery_text.text = "Battery: $battery"
        root.coolant_text.text = "Coolant: $coolant"
        root.brake_text.text = "Brakes: $brakes"
        root.transmission_text.text = "Transmission: $transmission"
        root.air_text.text = "Air Filter: $airFilter"


        root.dates_button.setOnClickListener {
            val navController = Navigation.findNavController(root)
            navController.navigate(R.id.datesFragment)
        }


        root.edit_profile_btn.setOnClickListener {
            val navController = Navigation.findNavController(root)
            navController.navigate(R.id.editProfileFragment)
        }

        // Define an array to store a list of users
        // its reference


        val db = FirebaseFirestore.getInstance()
        val usersCollectionRef = db.collection("Make_Appointment")


        val query = usersCollectionRef

        val options: FirestoreRecyclerOptions<AppointmentHistory> =
            FirestoreRecyclerOptions.Builder<AppointmentHistory>()
                .setQuery(query, AppointmentHistory::class.java)
                .build()


        // specify a viewAdapter for the data set
        appointmentAdapter = ProfileAdapter(options)

        // Store the the recyclerView widget in a variable
        val recyclerView = root.findViewById<RecyclerView>(R.id.profile_rv)

        // specify an viewAdapter for the dataset (we use dummy data containing 20 contacts)
        recyclerView.adapter = appointmentAdapter

        // use a linear layout manager, you can use different layouts as well
        recyclerView.layoutManager = LinearLayoutManager(root.context)

        // Add a divider between rows -- Optional
        val dividerItemDecoration = DividerItemDecoration(
            root.context,
            DividerItemDecoration.VERTICAL
        )
        recyclerView.addItemDecoration(dividerItemDecoration)

        // Ability to find appointments and change them
        // Make if (previous appt clicked then load data into appointment fragment and update the data in database?

        return root

    }

    override fun onStart() {
        super.onStart()
        appointmentAdapter.startListening()
    }

    override fun onStop() {
        super.onStop();
        appointmentAdapter.stopListening();
    }

}