package com.yelp.yelpapp.utility

import androidx.navigation.NavOptions
import com.yelp.yelpapp.R
import com.yelp.yelpapp.model.response.SpecialHour
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*

object Engine {

    fun distanceFormat(distance: Double): String {
        val formatter = NumberFormat.getNumberInstance(Locale.getDefault())
        var str = formatter.format(distance)
        str = StringBuilder(str).insert(str.length, " mi").toString()
        return str
    }

    fun reviewCount(review : Int) : String{
        val formatter = NumberFormat.getNumberInstance(Locale.getDefault())
        var str = formatter.format(review)
        str = StringBuilder(str).insert(str.length, " reviews").toString()
        return str
    }

     fun buildSpecialHours(spHours : List<SpecialHour>?) : String{
        var str = ""
        CoroutineScope(Dispatchers.Main).launch{
            spHours?.let {
                for(sh in spHours){
                    str = sh.date + "(start: ${sh.start}, end: ${sh.end})"
                }
            }

        }
        return str
    }

    fun popUpLeftRightNavigation() : NavOptions {
        return NavOptions.Builder()
            .setEnterAnim(R.anim.push_right_in)
            .setPopExitAnim(R.anim.push_left_out)
            .build()
    }
}