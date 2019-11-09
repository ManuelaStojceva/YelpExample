package com.yelp.yelpapp.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yelp.yelpapp.api.NoInternetException
import com.yelp.yelpapp.interfaces.SearchActionListener
import com.yelp.yelpapp.model.response.BusinessDetailResponse
import com.yelp.yelpapp.ui.base.BaseViewModel
import com.yelp.yelpapp.utility.Engine
import kotlinx.coroutines.launch

class BusinessDetailViewModel(
    private val repository: BusinessDetailRepository
) : BaseViewModel(repository){

    private var detailResult : MutableLiveData<BusinessDetailResponse> = MutableLiveData()
    val detailBusinessResult : LiveData<BusinessDetailResponse> =
        detailResult
    var searchListener : SearchActionListener? = null
    var open = "Open"
    var close = "Close"
    var specialOffer : MutableLiveData<String> = MutableLiveData()
    var categories : LiveData<String> = specialOffer

    init {
        specialOffer.value = ""
    }

    fun getBusinessDetailInfo(id : String){
        searchListener?.onStarted()
        viewModelScope.launch {
            try {
                val response = repository.getBusinessDetails(id)
                if(response == null){
                    searchListener?.onFailure(handleError())
                    return@launch
                }
                detailResult.value = response
                specialOffer.value = repository.buildCategories(response.categories)
            }catch (e : NoInternetException){
                searchListener?.onFailure(e.message!!)
            }
        }
    }
}