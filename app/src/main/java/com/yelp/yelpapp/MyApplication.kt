package com.yelp.yelpapp

import android.app.Application
import com.yelp.yelpapp.api.ApiService
import com.yelp.yelpapp.api.NetworkConnectionInterceptor
import com.yelp.yelpapp.ui.activity.ActivityRepository
import com.yelp.yelpapp.ui.activity.ActivityViewModelFactory
import com.yelp.yelpapp.ui.base.BaseRepository
import com.yelp.yelpapp.ui.base.BaseViewModelFactory
import com.yelp.yelpapp.ui.details.BusinessDetailRepository
import com.yelp.yelpapp.ui.details.BusinessDetailViewModelFactory
import com.yelp.yelpapp.ui.home.HomeSearchRepository
import com.yelp.yelpapp.ui.home.HomeViewModelFactory
import com.yelp.yelpapp.ui.search.SearchBusinessRepository
import com.yelp.yelpapp.ui.search.SearchBusinessViewModelFactory
import com.yelp.yelpapp.utility.PreferenceClass
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MyApplication : Application(), KodeinAware{

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@MyApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { ApiService(instance()) }
        bind() from singleton { PreferenceClass(instance()) }

        bind() from singleton { ActivityRepository(instance()) }
        bind() from singleton { HomeSearchRepository(instance(), instance()) }
        bind() from singleton { SearchBusinessRepository(instance(), instance()) }
        bind() from singleton { BusinessDetailRepository(instance()) }
        bind() from singleton { BaseRepository() }

        bind() from provider { ActivityViewModelFactory(instance()) }
        bind() from provider { HomeViewModelFactory(instance()) }
        bind() from provider { SearchBusinessViewModelFactory(instance()) }
        bind() from provider { BusinessDetailViewModelFactory(instance()) }
        bind() from provider { BaseViewModelFactory(instance()) }
    }
}