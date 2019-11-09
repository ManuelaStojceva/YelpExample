package com.yelp.yelpapp.utility

import android.content.Context
import android.location.LocationManager
import android.provider.Settings

class PreferenceClass(private val context: Context) {
    /*
        use application context to prevent memory leaks
     */
    private val appContext = context.applicationContext
    private var getLatLongObj: GetLatLongFromGPS? = null

    fun startGPS() {
        if(getLatLongObj == null)
            getLatLongObj = GetLatLongFromGPS(appContext)

        if (getLatLongObj != null)
            getLatLongObj!!.startGPS()
    }

    fun stopGPS() {
        if (getLatLongObj != null) {
            getLatLongObj!!.stopLocationListening()
        }
    }
    fun getGetLatLongObj(): GetLatLongFromGPS? {
        return getLatLongObj
    }

    fun checkEnableGPS(): Boolean {
        val locationProviders: String =
            Settings.Secure.getString(context.contentResolver, Settings.Secure.LOCATION_PROVIDERS_ALLOWED)

        if (locationProviders.contains(LocationManager.GPS_PROVIDER) && locationProviders.contains(
                LocationManager.NETWORK_PROVIDER)) {
            return true
        }
        return false
    }

}