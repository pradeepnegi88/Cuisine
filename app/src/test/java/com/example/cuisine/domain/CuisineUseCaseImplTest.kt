package com.example.cuisine.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cuisine.presenter.util.Constants
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CuisineUseCaseImplTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    private val query by lazy { Constants.RECIPE  }

    private lateinit var cuisineRepository: CuisineRepository

    private lateinit var cuisineUseCaseImpl: CuisineUseCaseImpl

    @Before
    fun setUp() {
        cuisineRepository = Mockito.mock(CuisineRepository::class.java)
        cuisineUseCaseImpl = CuisineUseCaseImpl(cuisineRepository)
    }


    @Test
    fun itShouldReturnListOfCuisine() {
        GlobalScope.launch{
             cuisineUseCaseImpl.getCuisine(query)
            Mockito.verify(cuisineRepository).getCuisine(query)}

    }

}