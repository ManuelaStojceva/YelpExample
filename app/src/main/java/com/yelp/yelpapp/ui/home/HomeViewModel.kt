package com.yelp.yelpapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yelp.yelpapp.api.NoInternetException
import com.yelp.yelpapp.interfaces.SearchActionListener
import com.yelp.yelpapp.model.response.Businesse
import com.yelp.yelpapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import java.lang.reflect.UndeclaredThrowableException

class HomeViewModel(
    private val repository: HomeSearchRepository
) : BaseViewModel(repository) {
    var searchListener : SearchActionListener? = null

    var searchEditText = MutableLiveData<String>()
    var searchResult = MutableLiveData<List<Businesse>>()
    val resultFromSearch : LiveData<List<Businesse>> = searchResult

    init {
        searchEditText.value = ""
    }

    fun businessSearch(){
        searchListener?.onStarted()
        viewModelScope.launch {
            try {
                val response = repository.businessSearch()
                if(response == null){
                    searchListener?.onFailure(handleError())
                    return@launch
                }
                searchResult.value = response.businesses

            }catch (e : NoInternetException){
                searchListener?.onFailure(e.message!!)
            }catch (e : UndeclaredThrowableException){
                e.cause?.message?.let { searchListener?.onFailure(it) }
            }
        }
    }

}