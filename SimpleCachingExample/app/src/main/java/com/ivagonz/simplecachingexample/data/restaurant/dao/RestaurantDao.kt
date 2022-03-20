package com.ivagonz.simplecachingexample.data.restaurant.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ivagonz.simplecachingexample.data.restaurant.dto.RestaurantDto
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurants")
    fun getAllRestaurants(): Flow<List<RestaurantDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurants(restaurants: List<RestaurantDto>)

    @Query("DELETE FROM restaurants")
    suspend fun deleteAllRestaurants()

}