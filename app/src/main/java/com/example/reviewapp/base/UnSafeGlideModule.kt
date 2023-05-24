package com.example.reviewapp.base

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.example.reviewapp.data.datasource.api.UnsafeOkHttpClient
import java.io.InputStream


@GlideModule
class UnSafeGlideModule:AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient()
        okHttpClient?.let {
            registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(okHttpClient))
        }
    }
}