package com.ivagonz.simplecachingexample.di

import androidx.room.Room
import com.ivagonz.simplecachingexample.RestaurantApplication
import com.ivagonz.simplecachingexample.common.Constants
import com.ivagonz.simplecachingexample.data.RestaurantDatabase
import com.ivagonz.simplecachingexample.data.restaurant.api.RestaurantApi
import com.ivagonz.simplecachingexample.data.restaurant.repository.RestaurantRepositoryImpl
import com.ivagonz.simplecachingexample.domain.repository.RestaurantRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideDatabase(app: RestaurantApplication): RestaurantDatabase =
        Room.databaseBuilder(app, RestaurantDatabase::class.java, Constants.DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideRestaurantApi(retrofit: Retrofit): RestaurantApi =
        retrofit.create(RestaurantApi::class.java)

    @Provides
    @Singleton
    fun provideRestaurantRepository(api: RestaurantApi): RestaurantRepository =
        RestaurantRepositoryImpl(api)


}