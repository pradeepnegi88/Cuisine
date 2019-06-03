package com.example.cuisine.presenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cuisine.BR
import com.example.cuisine.databinding.SinglePhotoLayoutBinding
import com.example.cuisine.domain.Cuisine


class CuisineAdapter(
    private val context: Context,
    private var photoList: List<Cuisine>,
    private var listener: OnItemClickListener
) :
    RecyclerView.Adapter<CuisineAdapter.ViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(item: Cuisine)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photoList[position], listener)
    }

    class ViewHolder(private val binding: SinglePhotoLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Cuisine, listener: OnItemClickListener) {
            binding.setVariable(BR.photo, data)
            binding.executePendingBindings()
            binding.linearLayout.setOnClickListener {
                listener.onItemClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: SinglePhotoLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            com.example.cuisine.R.layout.single_photo_layout,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = photoList.size
    fun setData(photoList: List<Cuisine>) {
        this.photoList = photoList
        notifyDataSetChanged()
    }

}