package com.ivagonz.simplecachingexample.presentation.main.recyclerview.comparator

import androidx.recyclerview.widget.DiffUtil
import com.ivagonz.simplecachingexample.data.Restaurant

class RestaurantItemComparator : DiffUtil.ItemCallback<Restaurant>() {

    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant) = oldItem.name == newItem.name


    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant) = oldItem == newItem
}