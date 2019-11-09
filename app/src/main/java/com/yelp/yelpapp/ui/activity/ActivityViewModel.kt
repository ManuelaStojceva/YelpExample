package com.yelp.yelpapp.ui.activity

import androidx.lifecycle.ViewModel

class ActivityViewModel(
    private val repository: ActivityRepository
) : ViewModel() {

    fun startGps(){
        repository.startGps()
    }
    fun stopGps(){
        repository.stopGps()
    }
    fun checkEnableGPS(): Boolean{
        return repository.checkEnableGPS()
    }
}