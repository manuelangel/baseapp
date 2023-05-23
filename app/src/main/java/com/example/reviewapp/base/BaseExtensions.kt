package com.example.reviewapp.base

import androidx.appcompat.widget.AppCompatImageView


fun AppCompatImageView.loadUrlImage(url:String){
    GlideApp.with(this).load(url).into(this)
}