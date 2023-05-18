package com.example.reviewapp.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T:ViewDataBinding> : AppCompatActivity(){

    protected lateinit var databinding: T

    protected abstract val layoutId : Int

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        databinding = DataBindingUtil.setContentView(this, layoutId)
    }

}