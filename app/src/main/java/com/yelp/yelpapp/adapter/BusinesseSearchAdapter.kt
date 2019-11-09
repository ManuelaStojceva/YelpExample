package com.yelp.yelpapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yelp.yelpapp.R
import com.yelp.yelpapp.interfaces.SearchActionListener
import com.yelp.yelpapp.model.response.Businesse
import com.yelp.yelpapp.utility.Engine
import kotlinx.android.synthetic.main.search_result_item.view.*

class BusinesseSearchAdapter(private val items : List<Businesse>, private val listener : SearchActionListener) : RecyclerView.Adapter<BusinesseSearchAdapter.BusinesseHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinesseHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_result_item, parent, false)
        return BusinesseHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BusinesseHolder, position: Int) {
        val item = items[position]
        holder.initView(item)
        holder.itemHolder.setOnClickListener { item.id?.let { it1 -> listener.onItemClicked(it1) } }
    }

    class BusinesseHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemHolder = itemView.item
        private val image = itemView.img
        private val name = itemView.name
        private val infos = itemView.info
        private val rating = itemView.ratingBar
        private val distance = itemView.distance
        fun initView(item : Businesse){
            if(!item.image_url.isNullOrEmpty())
                Picasso.get().load(item.image_url).into(image)
            item.name?.let { name.text = item.name }
            item.location?.let { infos.text = itemView.context.getString(R.string.ContactInfoAdd, item.location.address1, item.location.city, item.location.country, item.location.zip_code ) }
            item.distance?.let { distance.text = Engine.distanceFormat(item.distance) }
            item.rating?.let { rating.rating = item.rating }
        }
    }
}