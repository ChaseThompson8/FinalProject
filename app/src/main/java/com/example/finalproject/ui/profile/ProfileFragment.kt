package com.example.finalproject.ui.profile

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.AppointmentHistory
import com.example.finalproject.R
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var maintenanceAdapter: ArrayAdapter<String>
    private lateinit var maintenanceAdapter2: ArrayAdapter<String>
    private val TAG = "ProfileFragment"
    private val maintenanceList = ArrayList<String>()
    private val maintenanceList2 = ArrayList<String>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        maintenanceList.add("Oil change: ")
        maintenanceList.add("Air filter: ")
        maintenanceList.add("Battery: ")
        maintenanceList.add("Brake fluid: ")
        maintenanceList.add("Brake pads: ")
        maintenanceList.add("Brake rotors: ")

        maintenanceList2.add("Coolant")
        maintenanceList2.add("Transmission fluid: ")
        maintenanceList2.add("Power steering fluid:  ")
        maintenanceList2.add("Hoses: ")
        maintenanceList2.add("Spark plugs: ")
        maintenanceList2.add("Timing belt: ")

        // List View
        maintenanceAdapter = ArrayAdapter<String>(root.context, android.R.layout.simple_list_item_1, maintenanceList)
        root.lv_maintain.adapter = maintenanceAdapter

        // List View
        maintenanceAdapter2 = ArrayAdapter<String>(root.context, android.R.layout.simple_list_item_1, maintenanceList2)
        root.lv_maintain2.adapter = maintenanceAdapter2


        root.lv_maintain.setOnItemClickListener { _, _, position, _ ->

            when (position) {
                0 -> {
                    Log.d(TAG, maintenanceList[0])
                    updateDialog("Oil Change Update", "Oil Change", 0)
                }
                1 -> {
                    Log.d(TAG, maintenanceList[1])
                    updateDialog("Air Filter Update", "Air filter", 1)
                }
                2 -> {
                    Log.d(TAG, maintenanceList[2])
                    updateDialog("Battery Update", "Battery", 2)
                }
                3 -> {
                    Log.d(TAG, maintenanceList[3])
                    updateDialog("Brake Fluid Update", "Brake fluid", 3)
                }
                4 -> {
                    Log.d(TAG, maintenanceList[4])
                    updateDialog("Brake Pads Update", "Brake pads", 4)
                }
                else -> {
                    Log.d(TAG, maintenanceList[5])
                    updateDialog("Brake Rotors Update", "Brake rotors", 5)
                }
            }
        }
        root.lv_maintain2.setOnItemClickListener { _, _, position, _ ->

            when (position) {
                0 -> {
                    Log.d(TAG, maintenanceList2[0])
                    updateDialog2("Coolant Update", "Coolant", 0)
                }
                1 -> {
                    Log.d(TAG, maintenanceList2[1])
                    updateDialog2("Transmission Fluid Update", "Transmission fluid", 1)
                }
                2 -> {
                    Log.d(TAG, maintenanceList2[2])
                    updateDialog2("Power Steering Update", "Power steering", 2)
                }
                3 -> {
                    Log.d(TAG, maintenanceList2[3])
                    updateDialog2("Hoses Update", "Hoses", 3)
                }
                4 -> {
                    Log.d(TAG, maintenanceList2[4])
                    updateDialog2("Spark Plugs Update", "Spark plugs", 4)
                }
                else -> {
                    Log.d(TAG, maintenanceList2[5])
                    updateDialog2("Timing Belt Update", "Timing belt", 5)
                }
            }
        }

        root.edit_profile_btn.setOnClickListener {
            val navController = Navigation.findNavController(root)
            navController.navigate(R.id.editProfileFragment)
        }

        // Define an array to store a list of users
        val apptList = ArrayList<AppointmentHistory>()

        // specify a viewAdapter for the data set
        val adapter = ProfileAdapter(apptList)

        // Store the the recyclerView widget in a variable
        val recyclerView = root.findViewById<RecyclerView>(R.id.profile_rv)

        // specify an viewAdapter for the dataset (we use dummy data containing 20 contacts)
        recyclerView.adapter = adapter

        // use a linear layout manager, you can use different layouts as well
        recyclerView.layoutManager = LinearLayoutManager(root.context)

        // Add a divider between rows -- Optional
        val dividerItemDecoration = DividerItemDecoration(root.context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        // Ability to find appointments and change them
        // Make if (previous appt clicked then load data into appointment fragment and update the data in database?

        return root

    }

//    private fun generateContact(size: Int) : ArrayList<AppointmentHistory>{
//
//        val contacts = ArrayList<AppointmentHistory>()
//
//        // The for loop will generate [size] amount of contact data and store in a list
//        for (i in 1..size) {
//            val person = AppointmentHistory("Num-$i 4/20/21", "Damage repair", "car", "details")
//            contacts.add(person)
//        }
//        // return the list of contacts
//        return contacts
//    }

    private fun updateDialog(title: String, msg: String, pos: Int) {
        val builder = AlertDialog.Builder(activity)
        var userUpdate = ""
        builder.setIcon(android.R.drawable.ic_menu_edit)
        builder.setTitle(title)
        val ETUpdate = EditText(activity)
        ETUpdate.hint = "Updated $msg Date"
        builder.setView(ETUpdate)
        // Set the button actions (i.e. listeners), optional
        builder.setPositiveButton("OK"){ dialog, which ->
        // code to run when OK is pressed
            userUpdate = ETUpdate.text.toString()
            maintenanceList[pos] = "$msg: $userUpdate"
            maintenanceAdapter.notifyDataSetChanged()
            Log.d(TAG, "Updated: $userUpdate")
        }
        builder.setNeutralButton("Cancel"){ dialog, which ->
        // code to run when Cancel is pressed
        }
        // create the dialog and show it
        val dialog = builder.create()
        dialog.show()
    }
    private fun updateDialog2(title: String, msg: String, pos: Int) {
        val builder = AlertDialog.Builder(activity)
        var userUpdate = ""
        builder.setIcon(android.R.drawable.ic_menu_edit)
        builder.setTitle(title)
        val ETUpdate = EditText(activity)
        ETUpdate.hint = "Updated $msg Date"
        builder.setView(ETUpdate)
        // Set the button actions (i.e. listeners), optional
        builder.setPositiveButton("OK"){ dialog, which ->
            // code to run when OK is pressed
            userUpdate = ETUpdate.text.toString()
            maintenanceList2[pos] = "$msg: $userUpdate"
            maintenanceAdapter2.notifyDataSetChanged()
            Log.d(TAG, "Updated: $userUpdate")
        }
        builder.setNeutralButton("Cancel"){ dialog, which ->
            // code to run when Cancel is pressed
        }
        // create the dialog and show it
        val dialog = builder.create()
        dialog.show()
    }

//    private fun editProfileDialogue(title: String) {
//        val builder = AlertDialog.Builder(activity)
//        builder.setIcon(android.R.drawable.ic_menu_edit)
//        builder.setTitle(title)
//        val ETName = EditText(activity)
//        val ETEmail = EditText(activity)
//        ETName.hint = "Updated profile name"
//        ETEmail.hint = "Updated email address"
//        builder.setView(ETName)
//        builder.setView(ETEmail)
//        // Set the button actions (i.e. listeners), optional
//        builder.setPositiveButton("OK"){ dialog, which ->
//            // code to run when OK is pressed
//            val nameUpdate = ETName.text.toString()
//            val emailUpdate = ETEmail.text.toString()
//            if (nameUpdate.isNotEmpty()) {
//                profile_name.text = nameUpdate
//            }
//            if (emailUpdate.isNotEmpty()) {
//                profile_email.text = emailUpdate
//            }
//            Log.d(TAG, "Updated: $nameUpdate")
//            Log.d(TAG, "Updated: $emailUpdate")
//
//        }
//        builder.setNeutralButton("Cancel"){ dialog, which ->
//            // code to run when Cancel is pressed
//        }
//        // create the dialog and show it
//        val dialog = builder.create()
//        dialog.show()
//    }
}