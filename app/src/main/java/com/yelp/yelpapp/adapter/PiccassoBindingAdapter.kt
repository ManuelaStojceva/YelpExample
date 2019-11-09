package com.yelp.yelpapp.adapter

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.yelp.yelpapp.R
import com.yelp.yelpapp.model.response.SpecialHour
import com.yelp.yelpapp.utility.Engine

object PiccassoBindingAdapter {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun setImageResource(imageView: ImageView, imageUrl : String?){
        if(!imageUrl.isNullOrEmpty())
            Picasso.get().load(imageUrl).into(imageView)
    }
    @BindingAdapter("distance")
    @JvmStatic
    fun bindDistance(textView: TextView, distance : Double){
        textView.text = Engine.distanceFormat(distance)
    }
    @BindingAdapter("reviews")
    @JvmStatic
    fun bindReview(textView: TextView, count : Int){
        textView.text = Engine.reviewCount(count)
    }

    @BindingAdapter("color")
    @JvmStatic
    fun bindColor(textView: TextView, isClose : Boolean){
        if(isClose)
            textView.setTextColor(Color.RED)
        else
            textView.setTextColor(ContextCompat.getColor(textView.context, R.color.colorPrimary))
    }
    @BindingAdapter("specialoffers")
    @JvmStatic
    fun bindSpecialOffers(textView: TextView, specialHours : List<SpecialHour>?){
       textView.text = Engine.buildSpecialHours(specialHours)
    }

}