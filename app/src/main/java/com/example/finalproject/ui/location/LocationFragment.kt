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
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.fragment_location.view.*

class LocationFragment : Fragment(), OnMapReadyCallback {
    private lateinit var geocoder: Geocoder
    private val ACCESS_LOCATION_CODE = 125
    private lateinit var locationViewModel: LocationViewModel
    private lateinit var mMap: GoogleMap
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

        locationViewModel =
                ViewModelProvider(this).get(LocationViewModel::class.java)
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
    }

    override fun onMapReady(googleMap: GoogleMap) {

        Log.d("TAG", "onMapReady.... ")
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(41.123080, -73.416240)
        mMap.addMarker(MarkerOptions().position(sydney).title("The Shop"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
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
    private fun View.hideKeyboard() {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }

}