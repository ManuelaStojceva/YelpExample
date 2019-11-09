package com.yelp.yelpapp.ui.home

import com.yelp.yelpapp.BuildConfig
import com.yelp.yelpapp.api.ApiService
import com.yelp.yelpapp.model.response.BusnessSearchResponse
import com.yelp.yelpapp.ui.base.BaseRepository
import com.yelp.yelpapp.utility.AppConstants
import com.yelp.yelpapp.utility.PreferenceClass

class HomeSearchRepository(
    private val apiService: ApiService,
    private val preferenceClass: PreferenceClass
    ) : BaseRepository() {


    suspend fun businessSearch() : BusnessSearchResponse? {

        return apiRequest { apiService.businessSearch(AppConstants.Authorization + BuildConfig.API_Key, preferenceClass.getGetLatLongObj()?.getLatitude()!!,
            preferenceClass.getGetLatLongObj()?.getLongitude()!!,  AppConstants.Radius) }
    }
}