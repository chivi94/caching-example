package com.ivagonz.simplecachingexample.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivagonz.simplecachingexample.common.Resource
import com.ivagonz.simplecachingexample.data.restaurant.dto.toRestaurant
import com.ivagonz.simplecachingexample.domain.model.Restaurant
import com.ivagonz.simplecachingexample.domain.repository.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) : ViewModel() {

    private val _restaurantsList = MutableLiveData<Resource<List<Restaurant>>>()
    val restaurantsList: LiveData<Resource<List<Restaurant>>> = _restaurantsList

    init {
        viewModelScope.launch {

            try {
                _restaurantsList.postValue(Resource.Loading())

                delay(2000)

                val restaurants = restaurantRepository.getRestaurants()

                restaurants?.let { resultList ->
                    if (resultList.isNotEmpty()) {
                        _restaurantsList.postValue(Resource.Success(resultList.map { it.toRestaurant() }))
                    } else {
                        _restaurantsList.postValue(
                            Resource.Error(
                                "There is no restaurants."
                            )
                        )
                    }
                } ?: run {
                    _restaurantsList.postValue(
                        Resource.Error(
                            "There is no restaurants."
                        )
                    )
                }
            } catch (e: Exception) {
                _restaurantsList.postValue(
                    Resource.Error(
                        e.localizedMessage ?: "An unexpected error occurred."
                    )
                )
            }
        }
    }

}