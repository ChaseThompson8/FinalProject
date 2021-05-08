package com.example.finalproject

import com.google.firebase.Timestamp

data class AppointmentHistory( var DamageRepair: Boolean? = null,
                               var Maintenance: Boolean? = null,
                               var vehicleMake: String? = null,
                               var vehicleModel: String? = null,
                               var Other: Boolean? = null,
                               var vehicleDate: String? = null,
                               var vehicleDetails: String? = null,
                               var vehicleYear: Int? = null
)
