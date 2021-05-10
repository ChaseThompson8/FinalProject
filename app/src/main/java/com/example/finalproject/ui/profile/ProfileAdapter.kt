package com.example.finalproject.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.AppointmentHistory
import com.example.finalproject.R
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import java.text.SimpleDateFormat
import java.time.*
import java.util.*

class ProfileAdapter(options: FirestoreRecyclerOptions<AppointmentHistory>) :
    FirestoreRecyclerAdapter<AppointmentHistory, ProfileAdapter.MyViewHolder>(options) {
    val ints = 0;
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val apptDate = itemView.findViewById<TextView>(R.id.appt_date)
        val apptType = itemView.findViewById<TextView>(R.id.appt_type)
        val apptVehicle = itemView.findViewById<TextView>(R.id.appt_vehicle)
        val apptDetails = itemView.findViewById<TextView>(R.id.appt_details)

        // Set onClickListener to show a toast message for the selected row item in the list
        init {
            itemView.setOnClickListener{
                val selectedItem = adapterPosition



                Toast.makeText(itemView.context, "You clicked on $selectedItem",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item, parent, false)
        return MyViewHolder(view)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: AppointmentHistory) {
        val text = model.Maintenance.takeIf {model.Maintenance ==true}?.let { "Maintenance " }?:let { "" }
        val text_2 = model.DamageRepair.takeIf {model.DamageRepair==true}?.let { "DamageRepair " }?:let { "" }
        val text_3 =  model.Other.takeIf { model.Other ==true}?.let { "Other " }?:let { "" }
        var dateConvert =  model.vehicleDate
        var dataConverted = SimpleDateFormat("yyyy.MM.dd")
        holder.apptDate.text = "Date: "+  model.vehicleDate.toString()
        holder.apptType.text =  text + text_2 +text_3
        holder.apptVehicle.text = "Car: " +model.vehicleYear.toString() + model.vehicleMake.toString() + model.vehicleModel.toString()
        holder.apptDetails.text = "Details: "+ model.vehicleDetails.toString()
    }

   // 1620610815148
}