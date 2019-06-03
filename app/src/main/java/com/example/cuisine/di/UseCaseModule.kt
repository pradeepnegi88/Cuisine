package com.example.cuisine.di

import com.example.cuisine.domain.CuisineRepository
import com.example.cuisine.domain.CuisineUseCase
import com.example.cuisine.domain.CuisineUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun providesPhotoListUseCases(cuisineRepository: CuisineRepository): CuisineUseCase = CuisineUseCaseImpl(cuisineRepository)
}
