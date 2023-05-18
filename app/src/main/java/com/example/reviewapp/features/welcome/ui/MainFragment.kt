package com.example.reviewapp.features.welcome.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.reviewapp.features.welcome.vm.MainViewModel
import com.example.reviewapp.R
import com.example.reviewapp.base.BaseFragment
import com.example.reviewapp.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: BaseFragment<MainFragmentBinding>() {

    private val viewModel: MainViewModel by activityViewModels()

    override val layoutId: Int = R.layout.main_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("TEST_MANU","MainFragment Es null ${viewModel == null}")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        databinding.mainFragmentHeaderTv.text = viewModel.text
    }
}