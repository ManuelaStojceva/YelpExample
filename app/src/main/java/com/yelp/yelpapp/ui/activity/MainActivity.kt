package com.yelp.yelpapp.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yelp.yelpapp.R
import com.yelp.yelpapp.displayExplanationMessage
import com.yelp.yelpapp.displayGpsMessage
import com.yelp.yelpapp.displayMessage
import com.yelp.yelpapp.interfaces.LocationServicesListener
import com.yelp.yelpapp.utility.AppConstants
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.indices
import kotlin.collections.isNotEmpty
import kotlin.collections.set
import kotlin.collections.setOf
import kotlin.collections.toTypedArray

class MainActivity : AppCompatActivity(), KodeinAware, LocationServicesListener {

    override val kodein: Kodein by kodein()
    private val factory : ActivityViewModelFactory by instance()
    private val viewModel : ActivityViewModel by lazy {
        ViewModelProviders.of(this, factory).get(ActivityViewModel::class.java)
    }
    private var mProgressDialog: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        //set up navigation bar
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_search,
                R.id.navigation_about
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        mProgressDialog = this.pageLoadingIndicator
        checkAndRequestPermissions()
    }

    override fun onResume() {
        super.onResume()
        viewModel.startGps()
    }

    fun showProgress() {
        mProgressDialog?.visibility = View.VISIBLE
    }

    fun dismissProgress() {
        mProgressDialog?.visibility = View.GONE
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            AppConstants.ACCESS_LOCATION_REQUESTS ->{
                val perms = HashMap<String, Int>()
                perms[Manifest.permission.ACCESS_FINE_LOCATION] = PackageManager.PERMISSION_GRANTED
                if(grantResults.isNotEmpty()){
                    for (i in permissions.indices)
                        perms[permissions[i]] = grantResults[i]
                    if (perms[Manifest.permission.ACCESS_FINE_LOCATION] == PackageManager.PERMISSION_GRANTED) {
                        if(viewModel.checkEnableGPS())
                            viewModel.startGps()
                        else
                            displayGpsMessage()
                    }else{
                        if (ActivityCompat.shouldShowRequestPermissionRationale(
                                this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                            displayMessage()
                        }else
                            displayExplanationMessage()
                    }
                }
            }
        }
    }

    override fun onLocationDenided() {
        checkAndRequestPermissions()
    }
    override fun onLocationServices() {
        val myIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        startActivity(myIntent)
    }

    private fun checkAndRequestPermissions(): Boolean {

        val locationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val listPermissionsNeeded = ArrayList<String>()
        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (listPermissionsNeeded.isNotEmpty()) {

            ActivityCompat.requestPermissions(
                this, listPermissionsNeeded.toTypedArray(), AppConstants.ACCESS_LOCATION_REQUESTS
            )
            return false
        }
        return true
    }

    override fun onStop() {
        super.onStop()
        viewModel.stopGps()
    }
}
