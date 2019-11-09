package com.yelp.yelpapp.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "The Yelp Fusion API allows you to get the best local content and user reviews from millions of businesses across 32 countries.\\nThis application provides an overview of the capabilities our suite of APIs offer, provides instructions for how to authenticate API calls, and walks through a simple scenario using the API."
    }
    val text: LiveData<String> = _text
}