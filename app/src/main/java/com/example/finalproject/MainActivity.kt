package com.example.finalproject

//import com.firebase.

import android.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.fragment_appointment.*
import com.google.firebase.firestore.FirebaseFirestore
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.OnMapReadyCallback
//

class MainActivity : AppCompatActivity() {
    val TAG = "MyActivity"
    var db = FirebaseFirestore.getInstance()
    val usersCollectionRef = db.collection("Make_Appointment")
    private lateinit var mMap: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

//        supportActionBar?.hide()

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
                setOf(
                        R.id.navigation_home, R.id.navigation_appointment, R.id.navigation_location
                )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        //Firestore
        // Create a instance of the database and get
        // its reference

        val query = usersCollectionRef
        val options: FirestoreRecyclerOptions<AppointmentHistory> = FirestoreRecyclerOptions.Builder<AppointmentHistory>()
                .setQuery(query, AppointmentHistory::class.java)
                .build()
    }
    private fun generateContact(size: Int) : ArrayList<AppointmentHistory>{
        val contacts = ArrayList<AppointmentHistory>()
        for (i in 1..size) {
            val person = AppointmentHistory(
                    false,
                    false,
                    "Bruh",
                    "Ford",
                    false,
                    "5/18/19",
                    "Bruh",
                    1995
            )
            contacts.add(person)
        }
        return contacts
    }
        /*

        val mapFragment = SupportMapFragment.newInstance()
        supportFragmentManager
                .beginTransaction()
                .add(R.id.nav_host_fragment, mapFragment)
                .commit()
    }   */
    /*
    * ...setQuery(..., new SnapshotParser<Chat>() {
    @NonNull
    @Override
    public Chat parseSnapshot(@NonNull DataSnapshot snapshot) {
        return ...;
    }
});
    * *//*
    fun addFirebase() {

    */
    //Google Maps

}
