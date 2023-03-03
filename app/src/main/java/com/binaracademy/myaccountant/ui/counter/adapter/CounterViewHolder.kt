package com.binaracademy.myaccountant.ui.counter.adapter


import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.myaccountant.data.CounterData
import com.binaracademy.myaccountant.databinding.CounterChildBinding
import com.bumptech.glide.Glide

class CounterViewHolder(private val binding: CounterChildBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CounterData) {
        binding.textViewTitle.text = item.nama
        binding.imageCounter?.let { Glide.with(binding.root.context).load(item.url).into(it) }
        binding.textLocation.text = item.location
    }
}