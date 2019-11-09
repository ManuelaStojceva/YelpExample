package com.yelp.yelpapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yelp.yelpapp.R
import com.yelp.yelpapp.model.response.BusinessDetailResponse
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
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.detailBusinessResult.observe(this, Observer {
            setUi(it)
        })
    }

    private fun setUi(it: BusinessDetailResponse?) {
        it?.let {
            if(it.is_closed!!)
                isOpen.text = "CLOSE"
            else
                isOpen.text = "OPEN"
        }
    }

}