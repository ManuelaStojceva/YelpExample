package com.yelp.yelpapp.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class ActivityViewModelFactory(
    val repository: ActivityRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ActivityViewModel(repository) as T
    }
}