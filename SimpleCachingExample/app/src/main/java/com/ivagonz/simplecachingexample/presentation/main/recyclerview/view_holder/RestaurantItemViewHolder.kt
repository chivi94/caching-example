package com.ivagonz.simplecachingexample.presentation.main.recyclerview.view_holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ivagonz.simplecachingexample.R
import com.ivagonz.simplecachingexample.databinding.ViewHolderItemRestaurantBinding
import com.ivagonz.simplecachingexample.domain.model.Restaurant


class RestaurantItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding: ViewHolderItemRestaurantBinding =
        ViewHolderItemRestaurantBinding.bind(view)
    private val mContext = view.context


    fun bind(restaurantItem: Restaurant) {

        binding.apply {

            // Image
            Glide.with(mContext)
                .load(restaurantItem.logo)
                .error(R.mipmap.ic_launcher)
                .into(image)

            // Name
            name.text = restaurantItem.name ?: ""

            // Type
            type.text = restaurantItem.type ?: ""

            // Address
            address.text = restaurantItem.address ?: ""

        }

    }
}