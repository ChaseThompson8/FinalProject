package com.example.finalproject

//import com.firebase.
import android.util.Log
import android.*
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
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
import kotlinx.android.synthetic.main.fragment_location.*
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

    fun playSound(view: View) {
        if (myMediaPlayer == null){
            myMediaPlayer = MediaPlayer.create(this, R.raw.ehooga)
        }
        myMediaPlayer?.start()
    }
    private fun getLocationPermission(){

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            // Permission is granted
            enableUserLocation()
        } else {

            // Permission is not granted
            // show an explanation
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        ACCESS_LOCATION_CODE)

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        ACCESS_LOCATION_CODE)

                // ACCESS_LOCATION_CODE is an int constant (you decide a number). The callback method gets the
                // result of the request.
            }
        }
    }
    fun searchButton(view: View) {

        // Get address from the user
        val locationName = addressSearch.text.toString()
        addressSearch.hideKeyboard()
        //Log.d(TAG, "$locationName")

        if (locationName.isEmpty()){
            return
        }

        geocoder = Geocoder(this)

        try {
            val addressList = geocoder.getFromLocationName(locationName, 1)

            if (addressList.size > 0){

                val address = addressList[0]
                Log.d(ContentValues.TAG, "$address")
                // An address looks like below:
                // Address[addressLines=[0:"Boston"],
                // feature=Boston,admin=Massachusetts,sub-admin=Suffolk County,locality=Boston,
                // thoroughfare=null,postalCode=null,countryCode=US,countryName=United States,
                // hasLatitude=true,latitude=42.3600825,hasLongitude=true,
                // longitude=-71.0588801,phone=null,url=null,extras=null]

                // Convert to latitude and latitude to LatLng
                val latLng = LatLng(address.latitude, address.longitude)

                // Set the marker options
                val markerOptions = MarkerOptions()
                        .position(latLng)
                        .title(address.locality)
                        .snippet("Interesting place!") // You can change this text to something else

                // Add the marker
                mMap.addMarker(markerOptions)

                // Move and animate the camera, 12f is the zoom level, the higher number zooms more closely
                val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 12f)
                mMap.animateCamera(cameraUpdate)

            }
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "${e.message}")
        }

    }





    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            ACCESS_LOCATION_CODE -> {
                enableUserLocation()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun enableUserLocation() {
        mMap.isMyLocationEnabled = true
    }


    private fun View.hideKeyboard() {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }

}
