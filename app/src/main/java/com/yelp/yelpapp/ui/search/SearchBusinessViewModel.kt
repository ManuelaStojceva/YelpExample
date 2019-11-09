package com.yelp.yelpapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yelp.yelpapp.api.NoInternetException
import com.yelp.yelpapp.interfaces.SearchActionListener
import com.yelp.yelpapp.model.response.Businesse
import com.yelp.yelpapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SearchBusinessViewModel(
    private val repository: SearchBusinessRepository
) : BaseViewModel(repository) {

    var searchEditText = MutableLiveData<String>()
    var searchResult = MutableLiveData<List<Businesse>>()
    var searchListener : SearchActionListener? = null
    val resultBySearch : LiveData<List<Businesse>> = searchResult

    init {
        searchEditText.value = ""
    }

    fun businessSearchByCategory(){
        searchListener?.onStarted()
        viewModelScope.launch {
            try {
                val response = repository.businessSearchByCategory(searchEditText.value!!)
                if(response == null){
                    searchListener?.onFailure(handleError())
                    return@launch
                }
                searchResult.value = response.businesses
            }catch (e : NoInternetException){
                searchListener?.onFailure(e.message!!)
            }
        }
    }

}