package com.yelp.yelpapp.ui.search

import com.yelp.yelpapp.BuildConfig
import com.yelp.yelpapp.api.ApiService
import com.yelp.yelpapp.model.response.BusnessSearchResponse
import com.yelp.yelpapp.ui.base.BaseRepository
import com.yelp.yelpapp.utility.AppConstants
import com.yelp.yelpapp.utility.PreferenceClass

class SearchBusinessRepository(
    private val apiService: ApiService,
    private val preferenceClass: PreferenceClass
) : BaseRepository(){

    suspend fun businessSearchByCategory(category : String) : BusnessSearchResponse? {
        return apiRequest { apiService.businessSearchByCategory(
            AppConstants.Authorization + BuildConfig.API_Key, preferenceClass.getGetLatLongObj()?.getLatitude()!!,
            preferenceClass.getGetLatLongObj()?.getLongitude()!!,  category) }
    }

}