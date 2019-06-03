package com.example.cuisine.presenter

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cuisine.R
import com.example.cuisine.databinding.ActivityPhotoDescriptionBindingImpl
import com.example.cuisine.presenter.util.Constants
import kotlinx.android.synthetic.main.activity_photo_description.view.*


class CuisineDescriptionActivity : AppCompatActivity() {
    private val binding: ActivityPhotoDescriptionBindingImpl by lazy {
        DataBindingUtil.setContentView<ActivityPhotoDescriptionBindingImpl>(this, R.layout.activity_photo_description)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.photo = intent.getParcelableExtra(Constants.DATA)
        binding.executePendingBindings()
        binding.setLifecycleOwner(this)
        setContentView(binding.root)
        binding?.photo?.tags?.let {
            binding.root.tagText.apply {
                text = binding?.photo?.getStringFromTagsList()
                visibility = View.VISIBLE
            }
        }
        binding?.photo?.chef?.let {
            binding.root.chef.apply {
                text = it
                visibility = View.VISIBLE
            }
        }
    }

}
