package com.yelp.yelpapp.utility

import androidx.navigation.NavOptions
import com.yelp.yelpapp.R
import java.text.NumberFormat
import java.util.*

object Engine {

    fun distanceFormat(distance: Double): String {
        val formatter = NumberFormat.getNumberInstance(Locale.getDefault())
        var str = formatter.format(distance)
        str = StringBuilder(str).insert(str.length, " mi").toString()
        return str
    }

    fun popUpLeftRightNavigation() : NavOptions {
        return NavOptions.Builder()
            .setEnterAnim(R.anim.push_right_in)
            .setPopExitAnim(R.anim.push_left_out)
            .build()
    }
}