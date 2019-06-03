package com.example.cuisine.di

import com.contentful.java.cda.CDAClient
import com.example.cuisine.BuildConfig
import com.example.cuisine.data.CuisineRepositoryImpl
import com.example.cuisine.domain.CuisineRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun providesPhotoApiRepository(cdaClient: CDAClient): CuisineRepository = CuisineRepositoryImpl(cdaClient, "")

    @Provides
    @Singleton
    fun providesRetrofit(): CDAClient = CDAClient.builder()
        .setSpace(BuildConfig.SPACE)
        .setToken(BuildConfig.TOKEN)
        .build()
}