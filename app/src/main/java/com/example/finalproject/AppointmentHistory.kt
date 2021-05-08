package com.example.finalproject

import com.google.firebase.Timestamp
import java.sql.Time

data class AppointmentHistory( var DamageRepair: Boolean? = null,
                               var Maintenance: Boolean? = null,
                               var vehicleMake: String? = null,
                               var vehicleModel: String? = null,
                               var Other: Boolean? = null,
                               var vehicleDate: Timestamp? = null,
                               var vehicleDetails: String? = null,
                               var vehicleYear: String? = null
)
