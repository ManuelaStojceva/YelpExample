package com.yelp.yelpapp.interfaces

import com.yelp.yelpapp.model.response.Businesse

interface SearchActionListener {
    fun onStarted()
    fun onFailure(message : String)
    fun setUpView(businesses: List<Businesse>)
    fun onItemClicked(id : String)
}