package com.yelp.yelpapp.utility

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat

class GetLatLongFromGPS(val ctx : Context) {
    private var lmGPS: LocationManager? = null
    private var locationListener: LocationListener? = null
    private var mLatitude = 0.0
    private var mLongitude = 0.0

    fun startGPS(){
        if(lmGPS == null)
            lmGPS = ctx.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationListener == null)
            locationListener = MyLocationListener()
        if (ActivityCompat.checkSelfPermission(ctx,
               Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ctx,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return
        }
        var loc: Location? = null
        lmGPS?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 100f, locationListener!!)
        if (lmGPS?.isProviderEnabled(LocationManager.GPS_PROVIDER)!!) {
            lmGPS?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 100f, locationListener!!)
            loc = lmGPS?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        }
        if (loc == null)
            loc = lmGPS?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        updateLocation(loc)

    }
    fun stopLocationListening() {
        if (lmGPS != null && locationListener != null) {
            lmGPS?.removeUpdates(locationListener!!)
        }
    }
    fun getLatitude(): Double {
        return mLatitude
    }

    fun getLongitude(): Double {
        return mLongitude
    }
   inner class MyLocationListener : LocationListener {
        override fun onLocationChanged(loc: Location?) {

            loc?.let {
                if (loc.hasAccuracy()) {
                    updateLocation(loc)
                }
            }
        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
        }

        override fun onProviderEnabled(p0: String?) {
        }

        override fun onProviderDisabled(p0: String?) {
        }
    }

    private fun updateLocation(loc: Location?) {
        if (loc != null) {
            mLatitude = loc.latitude
            mLongitude = loc.longitude
        } else {
            mLatitude = 0.0
            mLongitude = 0.0
        }
    }
}