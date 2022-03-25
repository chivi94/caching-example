package com.ivagonz.simplecachingexample.presentation.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivagonz.simplecachingexample.common.Resource
import com.ivagonz.simplecachingexample.data.restaurant.dto.toRestaurant
import com.ivagonz.simplecachingexample.databinding.ActivityMainBinding
import com.ivagonz.simplecachingexample.presentation.main.recyclerview.adapter.RestaurantListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mViewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private lateinit var mContext: Context
    private lateinit var mLifeCycleOwner: LifecycleOwner
    private lateinit var mRestaurantAdapter: RestaurantListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mContext = this@MainActivity
        mLifeCycleOwner = this@MainActivity

        setupUI()

        setupObservers()
    }

    private fun setupUI() {

        binding.apply {

            recyclerView.layoutManager = LinearLayoutManager(mContext)
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    mContext,
                    LinearLayoutManager.VERTICAL
                )
            )
            mRestaurantAdapter = RestaurantListAdapter(mContext)
            recyclerView.adapter = mRestaurantAdapter

        }

    }

    private fun setupObservers() {

        mViewModel.restaurants.observe(mLifeCycleOwner) { result ->

            // New version
            //result.data?.let { mRestaurantAdapter.setFoods(it.map { restaurantDto -> restaurantDto.toRestaurant() }) }

            mRestaurantAdapter.submitList(result.data?.map { it.toRestaurant() })

            binding.apply {
                progress.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                error.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                recyclerView.isVisible = result is Resource.Success && !result.data.isNullOrEmpty()

                error.text = result.throwable?.localizedMessage
            }

            // Old version
            /*when (result) {

                is Resource.Loading -> {
                    binding.apply {
                        progress.isVisible = true
                        recyclerView.isVisible = false
                        error.isVisible = false
                    }
                }

                is Resource.Success -> {
                    binding.apply {
                        progress.isVisible = false
                        recyclerView.isVisible = true
                        error.isVisible = false
                    }

                    result.data?.let { mRestaurantAdapter.setFoods(it.map { restaurantDto -> restaurantDto.toRestaurant() }) }
                }

                is Resource.Error -> {
                    binding.apply {
                        progress.isVisible = false
                        recyclerView.isVisible = false
                        error.isVisible = true
                    }

                    result.throwable?.message.let { binding.error.text = it }
                }

            }*/

        }

    }
}