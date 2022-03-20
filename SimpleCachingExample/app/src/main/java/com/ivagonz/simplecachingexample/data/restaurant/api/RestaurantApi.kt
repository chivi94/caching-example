package com.ivagonz.simplecachingexample.data.restaurant.api

import com.ivagonz.simplecachingexample.data.restaurant.dto.RestaurantDto
import retrofit2.http.GET

interface RestaurantApi {

    @GET("restaurant/random_restaurant?size=20")
    suspend fun getRestaurants() : List<RestaurantDto>

}