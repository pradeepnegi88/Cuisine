package com.example.cuisine.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.contentful.java.cda.CDAClient
import com.example.cuisine.BuildConfig
import com.example.cuisine.domain.Cuisine
import com.example.cuisine.presenter.util.Constants
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, minSdk = 28)
class CuisineRepositoryImplTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var photoRepositoryImpl: CuisineRepositoryImpl
    private val query by lazy { Constants.RECIPE }

    @Before
    @Throws
    fun setUp() {
        val client = CDAClient.builder()
            .setSpace(BuildConfig.SPACE)
            .setToken(BuildConfig.TOKEN)
            .build()
        // Initialized repository
        photoRepositoryImpl = CuisineRepositoryImpl(client, query)
    }

    @Test
    fun itShouldReturnListOfCuisineReponse() = runBlocking {
        var cuisineResponse = emptyList<Cuisine>()
        Assert.assertTrue(cuisineResponse.isEmpty())
        cuisineResponse = photoRepositoryImpl.getCuisine(query)
        Assert.assertTrue(cuisineResponse.isNotEmpty())
    }


}