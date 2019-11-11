package com.yelp.yelpapp.ui.details

import com.yelp.yelpapp.BuildConfig
import com.yelp.yelpapp.api.ApiService
import com.yelp.yelpapp.model.response.BusinessDetailResponse
import com.yelp.yelpapp.model.response.Category
import com.yelp.yelpapp.ui.base.BaseRepository
import com.yelp.yelpapp.utility.AppConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class BusinessDetailRepository(
    private val apiService: ApiService
) : BaseRepository() {

    suspend fun getBusinessDetails(id : String) : BusinessDetailResponse?{
       return apiRequest { apiService.getBusinessDetails(AppConstants.Authorization + BuildConfig.API_Key, id) }
    }

    suspend fun buildCategories(categories : List<Category>?) : String{
        val builder = StringBuilder()
            builder.append("Categories: ")
        withContext(Dispatchers.Main){
            categories?.let {
                if(categories.isNotEmpty())
                    for(i in 0 until categories.size)
                         {
                            builder.append(categories[i].title)
                            if(i+1 <categories.size)
                                builder.append((", "))
                        }

            }

        }
        return builder.toString()
    }
}