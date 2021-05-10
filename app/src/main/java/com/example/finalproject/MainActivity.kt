package com.example.finalproject

//import com.firebase.
import android.util.Log
import android.*
import android.content.Intent
import android.os.Bundle
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
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
import com.firebase.ui.auth.AuthUI
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

//
import android.app.AlertDialog
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.android.synthetic.main.fragment_profile.*

import java.lang.StringBuilder
//
import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
//

import kotlinx.android.synthetic.main.activity_main.*
import android.media.MediaPlayer
import android.view.*
import kotlinx.android.synthetic.main.fragment_location.*
import kotlinx.android.synthetic.main.fragment_location.view.*

//

class MainActivity : AppCompatActivity() {
    val TAG = "MyActivity"
    var myMediaPlayer : MediaPlayer? = null
    private lateinit var geocoder: Geocoder
    private val ACCESS_LOCATION_CODE = 125
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

    }







}
