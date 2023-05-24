package com.example.reviewapp.features.welcome.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.example.reviewapp.features.welcome.vm.MainViewModel
import com.example.reviewapp.R
import com.example.reviewapp.base.BaseActivity
import com.example.reviewapp.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding>() {

    override val layoutId: Int = R.layout.main_activity

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(
            R.id.main_activity_fragment_container,
            MainFragment()
        ).commit()
    }
}