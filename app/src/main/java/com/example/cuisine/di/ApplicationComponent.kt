package com.example.cuisine.di

import com.example.cuisine.domain.CuisineRepository
import com.example.cuisine.domain.CuisineUseCase
import com.example.cuisine.presenter.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, UseCaseModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(app: MainActivity)
    fun providesPhotoApiRepository(): CuisineRepository
    fun providesPhotoListUseCases(): CuisineUseCase
}