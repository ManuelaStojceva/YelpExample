package com.yelp.yelpapp.ui.details

import com.yelp.yelpapp.BuildConfig
import com.yelp.yelpapp.api.ApiService
import com.yelp.yelpapp.model.response.BusinessDetailResponse
import com.yelp.yelpapp.ui.base.BaseRepository
import com.yelp.yelpapp.utility.AppConstants

class BusinessDetailRepository(
    private val apiService: ApiService
) : BaseRepository() {

    suspend fun getBusinessDetails(id : String) : BusinessDetailResponse?{
       return apiRequest { apiService.getBusinessDetails(AppConstants.Authorization + BuildConfig.API_Key, id) }
    }
}