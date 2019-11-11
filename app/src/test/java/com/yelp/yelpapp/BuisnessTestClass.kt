package com.yelp.yelpapp

import android.content.Context
import com.yelp.yelpapp.api.ApiService
import com.yelp.yelpapp.model.response.Businesse
import com.yelp.yelpapp.model.response.BusnessSearchResponse
import com.yelp.yelpapp.model.response.Center
import com.yelp.yelpapp.model.response.Region
import com.yelp.yelpapp.ui.home.HomeSearchRepository
import com.yelp.yelpapp.utility.AppConstants
import com.yelp.yelpapp.utility.PreferenceClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Response

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class BuisnessTestClass {
    @InjectMocks
    private lateinit var repository : HomeSearchRepository

    @Mock
    lateinit var apiServiceAce: ApiService
    @Mock
    lateinit var preferenceClass: PreferenceClass
    @Mock
    private lateinit var context: Context

    private  val center: Center = Center(0.0, 0.0)
    private val bussiness: List<Businesse> = listOf()
    private val region: Region = Region(center)



    @Before
    @Throws
    fun setUp() {
        context = mock(Context::class.java)
        preferenceClass = mock(PreferenceClass(context)::class.java)
        apiServiceAce = mock(ApiService::class.java)
        repository = HomeSearchRepository(apiServiceAce, preferenceClass)

    }

    @Test
    fun searchBusiness(){

        val value =Response.success(BusnessSearchResponse(bussiness, region, 8228))
        CoroutineScope(Dispatchers.IO).launch {
            Mockito.`when`(apiServiceAce.businessSearch(AppConstants.Authorization + BuildConfig.API_Key, 0.0, 0.0, AppConstants.Radius)).thenReturn(value)
        }
    }
}