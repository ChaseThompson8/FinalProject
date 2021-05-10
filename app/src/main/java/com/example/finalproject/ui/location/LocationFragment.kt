package com.example.finalproject.ui.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject.R
import android.util.Log
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
//
import android.location.Geocoder
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.fragment_location.*
import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.fragment_location.view.*
//

class LocationFragment : Fragment(), OnMapReadyCallback {
    private lateinit var geocoder: Geocoder
    private val ACCESS_LOCATION_CODE = 125
    private lateinit var mMap: GoogleMap
    var myMediaPlayer : MediaPlayer? = null
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            val root = inflater.inflate(R.layout.fragment_location, container, false)
         root.searchButton.setOnClickListener {
            searchButton(root);
        }


        return root

        }
        override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val mapView = activity?.findViewById<MapView>(R.id.mapView)
        mapView?.let {
            it.onCreate(savedInstanceState)
            it.onResume()
            it.getMapAsync(this)
        }

            storeA.setOnClickListener {
                val latLng= LatLng(41.599998, -72.699997)
                mMap.addMarker(MarkerOptions().position(latLng).title("Store A"))
                val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 12f)
                mMap.animateCamera(cameraUpdate)
            }
            storeB.setOnClickListener {
                val latLng= LatLng(42.599998, -73.699997)
                mMap.addMarker(MarkerOptions().position(latLng).title("Store B"))
                val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 12f)
                mMap.animateCamera(cameraUpdate)
            }
            storeC.setOnClickListener {
                val latLng = LatLng(43.599998, -74.699997)
                // Set the marker options
                mMap.addMarker(MarkerOptions().position(latLng).title("Store C"))
                // Move and animate the camera, 12f is the zoom level, the higher number zooms more closely
                val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 12f)
                mMap.animateCamera(cameraUpdate)

            }
    }

    override fun onMapReady(googleMap: GoogleMap) {

        Log.d("TAG", "onMapReady.... ")
        mMap = googleMap

        // Add a marker in Sydney and move the camera
       // val sydney = LatLng(41.123080, -73.416240)
       // mMap.addMarker(MarkerOptions().position(sydney).title("The Shop"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        getLocationPermission()
    }
    fun searchButton(view: View) {

        // Get address from the user
        val locationName = addressSearch.text.toString()
        addressSearch.hideKeyboard()
        //Log.d(TAG, "$locationName")

        if (locationName.isEmpty()){
            return
        }

        geocoder = Geocoder(activity?.applicationContext)

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
                        .snippet("Welcome to "  + addressSearch.text.toString()  ) // You can change this text to something else

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

    private fun getLocationPermission(){

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            // Permission is granted
            enableUserLocation()
        } else {

            // Permission is not granted
            // show an explanation
            if (shouldShowRequestPermissionRationale(
                            Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                requestPermissions(
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        ACCESS_LOCATION_CODE)

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(requireActivity(),
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        ACCESS_LOCATION_CODE)

                // ACCESS_LOCATION_CODE is an int constant (you decide a number). The callback method gets the
                // result of the request.
            }
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

    fun playSound(view: View) {
        if (myMediaPlayer == null){
            myMediaPlayer = MediaPlayer.create(this, R.raw.ehooga)
        }
        myMediaPlayer?.start()
    }
}