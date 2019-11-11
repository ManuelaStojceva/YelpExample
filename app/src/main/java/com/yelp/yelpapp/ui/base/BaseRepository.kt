package com.yelp.yelpapp.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yelp.yelpapp.api.SafeApiRequest

open class BaseRepository : SafeApiRequest() {

    private var errorMessage : MutableLiveData<String> = MutableLiveData()

    override fun exception(errorMsg: String, isUnauthorized: Boolean) {
        errorMessage.value = errorMsg
    }

    fun getErrorMessage() : LiveData<String> {return errorMessage}

}