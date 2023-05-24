package com.example.reviewapp.base

import androidx.recyclerview.widget.RecyclerView.Adapter

abstract class BaseRvAdapter<K,T:BaseViewHolder<K>>:Adapter<T>() {

    val items:ArrayList<K> = ArrayList()

    override fun getItemCount() = items.size

    fun addItems(newItems:List<K>){
        val startingIndex = items.size
        items.addAll(newItems)
        notifyItemRangeChanged(startingIndex,newItems.size)
    }

    fun setItems(newItems:List<K>){
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}