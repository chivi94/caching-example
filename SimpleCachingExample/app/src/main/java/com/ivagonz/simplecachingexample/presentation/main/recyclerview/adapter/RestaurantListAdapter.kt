package com.ivagonz.simplecachingexample.presentation.main.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ivagonz.simplecachingexample.R
import com.ivagonz.simplecachingexample.databinding.ViewHolderItemRestaurantBinding
import com.ivagonz.simplecachingexample.domain.model.Restaurant
import com.ivagonz.simplecachingexample.presentation.main.recyclerview.comparator.RestaurantItemComparator
import com.ivagonz.simplecachingexample.presentation.main.recyclerview.view_holder.RestaurantItemViewHolder

class RestaurantListAdapter(private val mContext: Context) : ListAdapter<Restaurant, RestaurantItemViewHolder>(RestaurantItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantItemViewHolder {
        return RestaurantItemViewHolder(
            LayoutInflater.from(mContext)
                .inflate(R.layout.view_holder_item_restaurant, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RestaurantItemViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }
}