package com.yelp.yelpapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.yelp.yelpapp.BR
import com.yelp.yelpapp.R
import com.yelp.yelpapp.interfaces.SearchActionListener
import com.yelp.yelpapp.model.response.Businesse
import kotlinx.android.synthetic.main.search_result_item.view.*

class BusinesseSearchAdapter(private val items : List<Businesse>, private val listener : SearchActionListener) : RecyclerView.Adapter<BusinesseSearchAdapter.BusinesseHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinesseHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : ViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.search_result_item, parent, false)
        return BusinesseHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BusinesseHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.binding.root.clickItem.setOnClickListener { item.id?.let { it1 ->
            listener.onItemClicked(
                it1
            )
        } }
    }

    inner class BusinesseHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : Businesse){
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }
}