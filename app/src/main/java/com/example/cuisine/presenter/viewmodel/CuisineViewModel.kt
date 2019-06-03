package com.example.cuisine.presenter.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.cuisine.R
import com.example.cuisine.domain.Cuisine
import com.example.cuisine.domain.CuisineUseCase
import com.example.cuisine.presenter.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@BindingAdapter("bind:imageUrl")
fun setImageUrl(view: ImageView, photo: Cuisine) {
    val requestOptions: RequestOptions by lazy {
        RequestOptions()
            .placeholder(R.mipmap.ic_launcher).diskCacheStrategy(DiskCacheStrategy.ALL)
    }


    Glide.with(view.context)
        .load(photo.photo)
        .apply(requestOptions)
        .into(view)

}

class CuisineViewModel @Inject constructor(private val cuisineUseCase: CuisineUseCase) : ViewModel() {
    private var cuisineMutableDataList: MutableLiveData<MutableList<Cuisine>> = MutableLiveData()
    var cuisineDataList: MutableLiveData<List<Cuisine>> = MutableLiveData()

    init {
        getAllPhoto(Constants.RECIPE)
    }

    private fun getAllPhoto(query: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val cuisineList = cuisineUseCase.getCuisine(query)
            cuisineMutableDataList.postValue(cuisineList as MutableList<Cuisine>?)
            cuisineDataList.postValue(cuisineList)
        }
    }


}
