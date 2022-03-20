package com.ivagonz.simplecachingexample.domain.repository

import com.ivagonz.simplecachingexample.data.restaurant.dto.RestaurantDto

interface RestaurantRepository {

    suspend fun getRestaurants(): List<RestaurantDto>?

}