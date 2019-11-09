package com.yelp.yelpapp.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
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
}