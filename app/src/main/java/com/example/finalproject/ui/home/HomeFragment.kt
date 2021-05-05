package com.example.finalproject.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.finalproject.R
import kotlinx.android.synthetic.main.fragment_confirmation.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val TAG = "HomeFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
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

//        val homeList = ArrayList<String>()
//        homeList.add("1")
//        homeList.add("2")
//        homeList.add("3")
//
//        // List View
//        val homeAdapter = ArrayAdapter<String>(view.context, android.R.layout.simple_list_item_1, homeList)
//        view.list_view.adapter = homeAdapter
//
//        //This allows us to make the list view clickable/react to our clicks
//        view.list_view.setOnItemClickListener { list, item, position, id ->
//            //Toast.makeText(this, "Hello- $id", Toast.LENGTH_SHORT).show()
//            val selectedItem = list.getItemAtPosition(position).toString()
//            Log.d(TAG, "$selectedItem")
//        }
        return root
    }
}