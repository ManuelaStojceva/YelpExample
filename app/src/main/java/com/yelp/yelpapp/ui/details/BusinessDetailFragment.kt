package com.yelp.yelpapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yelp.yelpapp.R
import com.yelp.yelpapp.adapter.SpecialHoursAdapter
import com.yelp.yelpapp.databinding.FragmentDetailsBinding
import com.yelp.yelpapp.ui.activity.MainActivity
import com.yelp.yelpapp.ui.base.BaseFragment
import com.yelp.yelpapp.utility.AppConstants
import kotlinx.android.synthetic.main.fragment_details.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class BusinessDetailFragment : BaseFragment(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val factory : BusinessDetailViewModelFactory by instance()
    private val viewModel : BusinessDetailViewModel by lazy {
        ViewModelProviders.of(this, factory).get(BusinessDetailViewModel::class.java)
    }
    private var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = arguments!!.getString(AppConstants.extraBusinessId)!!
        }
        viewModel.searchListener = this
        viewModel.getBusinessDetailInfo(id)
    }
    override fun onResume() {
        super.onResume()
        viewModel.searchListener = this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        if(activity is AppCompatActivity){
            (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
            (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.detailBusinessResult.observe(this, Observer {
            activity?.let { (activity as MainActivity).dismissProgress() }
        })
    }
}