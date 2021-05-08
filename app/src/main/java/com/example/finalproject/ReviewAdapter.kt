package com.example.finalproject.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.ReviewData
import com.example.finalproject.R
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import android.widget.RatingBar;
import kotlinx.android.synthetic.main.review_item.view.*

class ReviewAdapter(options: FirestoreRecyclerOptions<ReviewData>) :
FirestoreRecyclerAdapter<ReviewData, ReviewAdapter.MyViewHolder>(options) {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val description = itemView.findViewById<TextView>(R.id.review_description)
        val rating = itemView.findViewById<RatingBar>(R.id.review_rating)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.review_item, parent, false)
        return MyViewHolder(view)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: ReviewData) {
        holder.description.text = model.Details.toString()
        holder.rating.rating = model.Rating.toString().toFloat()
    }

}
