package com.ivagonz.simplecachingexample.data.restaurant.repository

import com.ivagonz.simplecachingexample.data.restaurant.api.RestaurantApi
import com.ivagonz.simplecachingexample.data.restaurant.dto.RestaurantDto
import com.ivagonz.simplecachingexample.domain.repository.RestaurantRepository

class RestaurantRepositoryImpl(
    private val api: RestaurantApi
) : RestaurantRepository {

    override suspend fun getRestaurants(): List<RestaurantDto> {
        return api.getRestaurants()
    }

}