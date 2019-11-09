package com.yelp.yelpapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.yelp.yelpapp.R
import com.yelp.yelpapp.adapter.BusinesseSearchAdapter
import com.yelp.yelpapp.databinding.FragmentSearchBinding
import com.yelp.yelpapp.displayErrorMessage
import com.yelp.yelpapp.hideKeyboard
import com.yelp.yelpapp.model.response.Businesse
import com.yelp.yelpapp.ui.activity.MainActivity
import com.yelp.yelpapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_search.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class SearchBusinessFragment : BaseFragment(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val factory : SearchBusinessViewModelFactory by instance()
    private val viewModel : SearchBusinessViewModel by lazy {
        ViewModelProviders.of(this, factory).get(SearchBusinessViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
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
        search.setOnEditorActionListener { _, _, _ ->
            activity?.hideKeyboard(search)
            viewModel.businessSearchByCategory()
            true
        }

        viewModel.resultBySearch.observe(this, Observer {
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
        super.setUpView(businesses)
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.setHasFixedSize(true)
        recycleView.adapter = BusinesseSearchAdapter(businesses, this).apply { notifyDataSetChanged() }
    }
}