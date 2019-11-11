package com.yelp.yelpapp.ui.search

import androidx.lifecycle.ViewModel
import com.yelp.yelpapp.ui.base.BaseViewModelFactory

@Suppress("UNCHECKED_CAST")
class SearchBusinessViewModelFactory(
    private val repository: SearchBusinessRepository
) : BaseViewModelFactory(repository){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchBusinessViewModel(repository) as T
    }
}