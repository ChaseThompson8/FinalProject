package com.example.finalproject

import com.google.firebase.Timestamp
import java.sql.Time

data class AppointmentHistory( var DamageRepair: String? = null,
                               var Maintenance: String? = null,
                               var vehicleMake: String? = null,
                               var vehicleModel: String? = null,
                               var Other: String? = null,
                               var vehicleDate: Double? = null,
                               var vehicleDetails: String? = null,
                               var vehicleYear: String? = null
)
