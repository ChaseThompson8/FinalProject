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


class LocationFragment : Fragment(), OnMapReadyCallback {

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





}