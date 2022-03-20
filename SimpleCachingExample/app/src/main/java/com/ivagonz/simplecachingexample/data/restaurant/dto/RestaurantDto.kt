package com.ivagonz.simplecachingexample.data.restaurant.dto

import com.ivagonz.simplecachingexample.domain.model.Restaurant

data class RestaurantDto(
    val name: String?,
    val type: String?,
    val logo: String?,
    val address: String?
)

fun RestaurantDto.toRestaurant(): Restaurant {
    return Restaurant(
        name = name,
        type = type,
        logo = logo,
        address = address
    )
}