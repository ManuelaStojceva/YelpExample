package com.yelp.yelpapp.ui.activity

import com.yelp.yelpapp.utility.PreferenceClass

class ActivityRepository(
    private val prefs : PreferenceClass
) {

    fun startGps(){
        prefs.startGPS()
    }
    fun stopGps(){
        prefs.stopGPS()
    }
    fun checkEnableGPS(): Boolean{
        return prefs.checkEnableGPS()
    }
}