package com.ivagonz.simplecachingexample.data.restaurant.repository

import androidx.room.withTransaction
import com.ivagonz.simplecachingexample.common.Resource
import com.ivagonz.simplecachingexample.common.networkBoundResource
import com.ivagonz.simplecachingexample.data.RestaurantDatabase
import com.ivagonz.simplecachingexample.data.restaurant.api.RestaurantApi
import com.ivagonz.simplecachingexample.data.restaurant.dao.RestaurantDao
import com.ivagonz.simplecachingexample.data.restaurant.dto.RestaurantDto
import com.ivagonz.simplecachingexample.domain.repository.RestaurantRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val api: RestaurantApi,
    private val db: RestaurantDatabase
) : RestaurantRepository {


    private val restaurantDao = db.restaurantDao()

    override fun getRestaurants(): Flow<Resource<List<RestaurantDto>>> =
        networkBoundResource(
            query = {
                restaurantDao.getAllRestaurants()
            },
            fetch = {
                delay(2000)
                api.getRestaurants()
            },
            saveFetchResult = { restaurants ->
                // With transaction, if one operation fails, all operations will fail
                db.withTransaction {
                    restaurantDao.deleteAllRestaurants()
                    restaurantDao.insertRestaurants(restaurants)
                }
            }
        )
}