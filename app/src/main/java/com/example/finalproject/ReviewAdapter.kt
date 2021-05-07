package com.example.finalproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReviewAdapter(private val reviewDataSet: ArrayList<ReviewData>):
        RecyclerView.Adapter<ReviewAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val rating = itemView.findViewById<TextView>(R.id.review_rating)
        val description = itemView.findViewById<TextView>(R.id.review_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.review_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = reviewDataSet[position]
        holder.rating.text = currentItem.rating
        holder.description.text = currentItem.description
    }

    override fun getItemCount(): Int {
        return reviewDataSet.size
    }


}