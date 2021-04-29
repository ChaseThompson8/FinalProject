package com.example.finalproject.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.AppointmentHistory
import com.example.finalproject.R

class ProfileAdapter(private val profileDataSet: ArrayList<AppointmentHistory>):
        RecyclerView.Adapter<ProfileAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val apptDate = itemView.findViewById<TextView>(R.id.appt_date)
        val apptType = itemView.findViewById<TextView>(R.id.appt_type)
        val apptVehicle = itemView.findViewById<TextView>(R.id.appt_vehicle)
        val apptDetails = itemView.findViewById<TextView>(R.id.appt_details)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = profileDataSet[position]
        holder.apptDate.text = currentItem.date
        holder.apptType.text = currentItem.type
        holder.apptVehicle.text = currentItem.vehicle
        holder.apptDetails.text = currentItem.details
    }

    override fun getItemCount(): Int {
        return profileDataSet.size
    }


}