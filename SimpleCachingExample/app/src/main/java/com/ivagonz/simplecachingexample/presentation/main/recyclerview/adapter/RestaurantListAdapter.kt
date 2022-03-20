package com.ivagonz.simplecachingexample.presentation.main.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ivagonz.simplecachingexample.R
import com.ivagonz.simplecachingexample.data.Restaurant
import com.ivagonz.simplecachingexample.presentation.main.recyclerview.comparator.RestaurantItemComparator
import com.ivagonz.simplecachingexample.presentation.main.recyclerview.view_holder.RestaurantItemViewHolder

class RestaurantListAdapter(private val mContext: Context) : ListAdapter<Restaurant, RestaurantItemViewHolder>(RestaurantItemComparator()) {

    private val mRestaurantsList = arrayListOf<Restaurant>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantItemViewHolder {
        return RestaurantItemViewHolder(
            LayoutInflater.from(mContext)
                .inflate(R.layout.view_holder_item_restaurant, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RestaurantItemViewHolder, position: Int) {
        holder.bind(mRestaurantsList[position])
    }

    override fun getItemCount(): Int = mRestaurantsList.size

    fun setFoods(newRestaurants: ArrayList<Restaurant>) {
        mRestaurantsList.clear()
        mRestaurantsList.addAll(newRestaurants)
        notifyItemRangeChanged(0, newRestaurants.size)
    }
}