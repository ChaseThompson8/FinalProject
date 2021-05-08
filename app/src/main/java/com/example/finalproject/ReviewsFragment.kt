package com.example.finalproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.ui.profile.ReviewAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_reviews.view.*
import com.example.finalproject.ReviewData
import kotlinx.android.synthetic.main.review_item.*
import android.widget.RatingBar
import android.*
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.fragment_appointment.*



class ReviewsFragment : Fragment() {
    lateinit var reviewAdapter: ReviewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_reviews, container, false)

        // Define an array to store a list of users
       // val reviewList = ArrayList<ReviewData>()

        val db = FirebaseFirestore.getInstance()
        val usersCollectionRef = db.collection("Make_Review")
        val query = usersCollectionRef
        val options: FirestoreRecyclerOptions<ReviewData> = FirestoreRecyclerOptions.Builder<ReviewData>()
                .setQuery(query, ReviewData::class.java)
                .build()
        // specify a viewAdapter for the data set
        reviewAdapter = ReviewAdapter(options)

        // Store the the recyclerView widget in a variable
        val recyclerView = root.findViewById<RecyclerView>(R.id.review_recycler)
        // specify an viewAdapter for the dataset (we use dummy data containing 20 contacts)

        recyclerView.adapter = reviewAdapter
        // use a linear layout manager, you can use different layouts as well
        recyclerView.layoutManager = LinearLayoutManager(root.context)

        // Add a divider between rows -- Optional
        val dividerItemDecoration = DividerItemDecoration(root.context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        return root
    }

    override fun onStart() {
        super.onStart()
        reviewAdapter.startListening()
    }
    override fun onStop() {
        super.onStop();
        reviewAdapter.stopListening();
    }
}