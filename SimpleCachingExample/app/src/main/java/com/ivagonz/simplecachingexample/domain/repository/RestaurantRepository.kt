package com.ivagonz.simplecachingexample.domain.repository

import com.ivagonz.simplecachingexample.common.Resource
import com.ivagonz.simplecachingexample.data.restaurant.dto.RestaurantDto
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository {

    fun getRestaurants(): Flow<Resource<List<RestaurantDto>>>

}