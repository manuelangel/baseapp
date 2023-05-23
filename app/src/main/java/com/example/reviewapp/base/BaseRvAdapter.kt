package com.example.reviewapp.base

import androidx.recyclerview.widget.RecyclerView.Adapter

abstract class BaseRvAdapter<K,T:BaseViewHolder<K>>:Adapter<T>() {

    val items:ArrayList<K> = ArrayList()

    override fun getItemCount() = items.size

    fun addItems(newItems:List<K>){
        items.addAll(newItems)
        notifyDataSetChanged()
    }

}