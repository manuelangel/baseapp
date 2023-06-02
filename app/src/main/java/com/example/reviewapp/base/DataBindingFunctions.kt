package com.example.reviewapp.base

import android.graphics.drawable.Drawable

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView

import androidx.databinding.BindingAdapter


@BindingAdapter("app:srcCompat")
fun bindSrcCompat(imageView: AppCompatImageView?, drawable: Drawable?) {
    drawable?.let{
        imageView?.setImageDrawable(it)
    }
}