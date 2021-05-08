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
        holder.apptDate.text = model.vehicleDate
        holder.apptType.text = model.Maintenance.toString() + model.DamageRepair.toString() + model.Other.toString()
        holder.apptVehicle.text = model.vehicleYear.toString() + model.vehicleMake.toString() + model.vehicleModel.toString()
        holder.apptDetails.text = model.vehicleDetails.toString()

        /* ??
    * FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Chat, ChatHolder>(options) {
    @Override
    public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new instance of the ViewHolder, in this case we are using a custom
        // layout called R.layout.message for each item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message, parent, false);

        return new ChatHolder(view);
    }

    @Override
    protected void onBindViewHolder(ChatHolder holder, int position, Chat model) {
        // Bind the Chat object to the ChatHolder
        // ...
    }
};
    * */
    }
}