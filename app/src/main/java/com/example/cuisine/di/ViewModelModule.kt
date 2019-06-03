package com.example.cuisine.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cuisine.presenter.viewmodel.CuisineViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pradeep.com.negi.weatherapp.viewmodel.viewmodelprovider.ViewModelFactory
import pradeep.com.negi.weatherapp.viewmodel.viewmodelprovider.ViewModelKey

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CuisineViewModel::class)
    internal abstract fun postListViewModel(viewModel: CuisineViewModel): ViewModel

}