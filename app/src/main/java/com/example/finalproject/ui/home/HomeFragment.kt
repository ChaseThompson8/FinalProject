package com.example.finalproject.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.finalproject.R
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    private val TAG = "HomeFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        root.book_button.setOnClickListener {
            val navController = Navigation.findNavController(root)
            navController.navigate(R.id.navigation_appointment)
        }
        root.about_button.setOnClickListener {
            val navController = Navigation.findNavController(root)
            navController.navigate(R.id.aboutFragment)
        }
        root.review_button.setOnClickListener {
            val navController = Navigation.findNavController(root)
            navController.navigate(R.id.reviewsFragment)
        }
        root.leave_review_btn.setOnClickListener {
            val navController = Navigation.findNavController(root)
            navController.navigate(R.id.makeReviewFragment)
        }
        root.call_button.setOnClickListener {
            // Dial a phone number
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:1234567890")
            startActivity(callIntent)
        }
        return root
    }
}