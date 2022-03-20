package com.ivagonz.simplecachingexample.data.restaurant.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ivagonz.simplecachingexample.domain.model.Restaurant

@Entity(tableName = "restaurants")
data class RestaurantDto(
    @PrimaryKey val name: String?,
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