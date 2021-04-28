package com.example.finalproject.ui.appointment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject.R
import kotlinx.android.synthetic.main.activity_confirmation.*
import kotlinx.android.synthetic.main.activity_confirmation.view.*

class ConfirmationFragment : Fragment() {

    private lateinit var confirmationViewModel: ConfirmationViewModel
    val TAG = "ConfirmationFragment"
    var make = ""
    var model = ""
    var year = ""
    var date = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        confirmationViewModel =
            ViewModelProvider(this).get(ConfirmationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_confirmation, container, false)
//        val textView: TextView = root.findViewById(R.id.text_appointment)
        confirmationViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })

        val sharedPreference = activity?.getPreferences(Context.MODE_PRIVATE)
        val makeInfo = sharedPreference?.getString("Make", make) ?: ""
        val modelInfo = sharedPreference?.getString("Model", model) ?: ""
        val yearInfo = sharedPreference?.getString("Year", year) ?: ""
        val dateInfo = sharedPreference?.getString("Date", date) ?: ""

//        val makeInfo = intent.getStringExtra("Make")
//        val modelInfo = intent.getStringExtra("Model")
//        val yearInfo = intent.getStringExtra("Year")
//        val dateInfo = intent.getStringExtra("Date")

        root.confirmation_body.text = "You appointment for your $yearInfo $makeInfo $modelInfo is all set for $dateInfo."

        return root
    }
}
