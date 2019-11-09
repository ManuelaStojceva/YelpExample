package com.yelp.yelpapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.yelp.yelpapp.BR
import com.yelp.yelpapp.R
import com.yelp.yelpapp.model.response.SpecialHour
import java.util.*

class SpecialHoursAdapter(private val spItems : List<SpecialHour>) : RecyclerView.Adapter<SpecialHoursAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : ViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.special_hours_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return spItems.size   }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sh = spItems[position]
        holder.bind(sh)
    }

    inner class ViewHolder(private val binding : ViewDataBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(sh : SpecialHour){
            binding.setVariable(BR.category, sh)
            binding.executePendingBindings()
        }
    }
}