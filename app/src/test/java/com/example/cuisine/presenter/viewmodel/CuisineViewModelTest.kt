package com.example.cuisine.presenter.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.cuisine.domain.Cuisine
import com.example.cuisine.domain.CuisineUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CuisineViewModelTest {
    private val cuisineUseCase: CuisineUseCase by lazy { Mockito.mock(CuisineUseCase::class.java) }
    private lateinit var cuisineViewModel: CuisineViewModel
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        cuisineViewModel = CuisineViewModel(cuisineUseCase)
    }

    @Test
    fun shouldGetWeatherPostsAfterUpdate() {
        val cuisine = Cuisine(title = "TITILE",description = "desc",chef = "CHEF",photo = "PHOTO",tags = null,calorie = 33.0)
        val observer = Mockito.mock(Observer::class.java) as Observer<List<Cuisine>>
        cuisineViewModel.cuisineDataList.observeForever(observer)
        //when
        cuisineViewModel.cuisineDataList.value = listOf(cuisine)
        //than
        verify(observer).onChanged(
            listOf(Cuisine(title = cuisine.title, description = cuisine.description, chef = cuisine.chef,photo = cuisine.photo,calorie = cuisine.calorie,tags = cuisine.tags))
        )
    }
}