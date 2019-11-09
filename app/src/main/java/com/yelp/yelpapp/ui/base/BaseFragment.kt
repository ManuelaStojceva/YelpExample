package com.yelp.yelpapp.ui.base

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.yelp.yelpapp.R
import com.yelp.yelpapp.displayErrorMessage
import com.yelp.yelpapp.interfaces.SearchActionListener
import com.yelp.yelpapp.model.response.Businesse
import com.yelp.yelpapp.ui.activity.MainActivity
import com.yelp.yelpapp.utility.AppConstants
import com.yelp.yelpapp.utility.Engine

open class BaseFragment : Fragment(), SearchActionListener {

    override fun onStarted() {
        activity?.let { (activity as MainActivity).showProgress() }
    }

    override fun onFailure(message: String) {
        activity?.let { (activity as MainActivity).dismissProgress() }
        if (message.isNotEmpty())
            activity?.displayErrorMessage(message)
        else
            activity?.displayErrorMessage(getString(R.string.GenericError))
    }

    override fun setUpView(businesses: List<Businesse>) {}

    override fun onItemClicked(id: String) {
        val bundle = bundleOf(AppConstants.extraBusinessId to id)
        findNavController().navigate(R.id.action_navigation_home_to_navigation_detail, bundle, Engine.popUpLeftRightNavigation())
    }
}