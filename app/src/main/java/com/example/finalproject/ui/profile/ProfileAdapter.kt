package com.example.finalproject.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.AppointmentHistory
import com.example.finalproject.R
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.firebase.ui.firestore.FirestoreRecyclerAdapter

class ProfileAdapter(options: FirestoreRecyclerOptions<AppointmentHistory>) :
    FirestoreRecyclerAdapter<AppointmentHistory, ProfileAdapter.MyViewHolder>(options) {
    val ints = 0;
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: AppointmentHistory) {
        holder.apptDate.text = "Date: "+model.vehicleDate.toString()
        holder.apptType.text = model.Maintenance.toString() + model.DamageRepair.toString() + model.Other.toString()
        holder.apptVehicle.text = "Car: " +model.vehicleYear.toString() + model.vehicleMake.toString() + model.vehicleModel.toString()
        holder.apptDetails.text = "Details: "+ model.vehicleDetails.toString()


    }
}