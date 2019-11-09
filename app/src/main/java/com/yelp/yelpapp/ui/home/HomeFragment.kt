package com.yelp.yelpapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yelp.yelpapp.R
import com.yelp.yelpapp.adapter.BusinesseSearchAdapter
import com.yelp.yelpapp.databinding.FragmentHomeBinding
import com.yelp.yelpapp.displayErrorMessage
import com.yelp.yelpapp.model.response.Businesse
import com.yelp.yelpapp.ui.activity.MainActivity
import com.yelp.yelpapp.ui.base.BaseFragment
import com.yelp.yelpapp.utility.AppConstants
import com.yelp.yelpapp.utility.Engine
import kotlinx.android.synthetic.main.fragment_home.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class HomeFragment : BaseFragment(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val factory : HomeViewModelFactory by instance()
    private val viewModel : HomeViewModel by lazy {
        ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.searchListener = this
        viewModel.apply { businessSearch() }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.searchListener = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.resultFromSearch.observe(this, Observer {
            activity?.let { (activity as MainActivity).dismissProgress() }
            it?.let {
                if(it.isEmpty())
                    activity?.displayErrorMessage(getString(R.string.noResultFoundMsg))
                else{
                    setUpView(it)
                }
            }
        })
    }

    override fun setUpView(businesses: List<Businesse>) {
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.setHasFixedSize(true)
        recycleView.adapter = BusinesseSearchAdapter(businesses, this).apply { notifyDataSetChanged() }
    }
}