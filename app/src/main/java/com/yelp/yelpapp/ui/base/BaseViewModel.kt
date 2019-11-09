package com.yelp.yelpapp.ui.base

import androidx.lifecycle.ViewModel

open class BaseViewModel(
    private val repository: BaseRepository
) : ViewModel(){

    fun handleError() : String{
        val error = repository.getErrorMessage().value
        error?.let {
            return error
        }
        return ""
    }
}