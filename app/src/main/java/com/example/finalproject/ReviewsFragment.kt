package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.ui.profile.ProfileAdapter

class ReviewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_reviews, container, false)

        // Define an array to store a list of users
        val reviewList = ArrayList<ReviewData>()

        // specify a viewAdapter for the data set
        val adapter = ReviewAdapter(reviewList)

        // Store the the recyclerView widget in a variable
        val recyclerView = root.findViewById<RecyclerView>(R.id.review_recycler)

        // specify an viewAdapter for the dataset (we use dummy data containing 20 contacts)
        recyclerView.adapter = adapter

        // use a linear layout manager, you can use different layouts as well
        recyclerView.layoutManager = LinearLayoutManager(root.context)

        // Add a divider between rows -- Optional
        val dividerItemDecoration = DividerItemDecoration(root.context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        return root
    }
}