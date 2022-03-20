package com.ivagonz.simplecachingexample.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ivagonz.simplecachingexample.data.restaurant.dao.RestaurantDao
import com.ivagonz.simplecachingexample.data.restaurant.dto.RestaurantDto

@Database(
    entities = [RestaurantDto::class],
    version = 1
)
abstract class RestaurantDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

}