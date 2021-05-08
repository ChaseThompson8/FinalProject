package com.example.finalproject

//import com.firebase.
import android.util.Log;
import android.*
import android.os.Bundle
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
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
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_make_review.*
import kotlinx.android.synthetic.main.review_item.*

//

class MainActivity : AppCompatActivity() {
    val TAG = "MyActivity"
    lateinit var Rating_Bar : RatingBar
    lateinit var Details_Text : TextView

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

        // set on-click listener


    }

    /*
    fun addData(){
        val db = FirebaseFirestore.getInstance()
        val appointment = db.collection("cities")
        val cityInfo = hashMapOf(
                "Maintance" to "San Francisco",
                "state" to "CA",
                "country" to "USA",
                "capital" to false,
                "population" to 860000,
                "regions" to listOf("west_coast"
                        ,
                        "norcal")
        )
        appointment.document.set(cityInfo)

    }*/

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
