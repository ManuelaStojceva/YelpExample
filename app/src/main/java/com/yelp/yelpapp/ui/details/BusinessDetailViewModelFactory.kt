package com.yelp.yelpapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class BusinessDetailViewModelFactory(private val repository: BusinessDetailRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BusinessDetailViewModel(repository) as T
    }
}