package com.example.reviewapp.features.welcome.ui

import android.os.Bundle
import android.util.Log
import android.util.SparseIntArray
import android.view.View
import androidx.core.util.contains
import androidx.fragment.app.activityViewModels
import com.example.reviewapp.R
import com.example.reviewapp.base.BaseFragment
import com.example.reviewapp.base.loadUrlImage
import com.example.reviewapp.databinding.MainFragmentBinding
import com.example.reviewapp.features.welcome.ui.adapter.PhotosAdapter
import com.example.reviewapp.features.welcome.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: BaseFragment<MainFragmentBinding>() {

    private val viewModel: MainViewModel by activityViewModels()

    private val adapter = PhotosAdapter()

    override val layoutId: Int = R.layout.main_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        databinding.imagen.loadUrlImage("https://picsum.photos/200")
        databinding.mainFragmentHeaderTv.text = viewModel.text
        setObservers()
        setListeners()
        initRv()
    }

    private fun initRv() {
        databinding.mainFragmentPhotosRv.adapter = adapter
    }

    private fun setObservers() {

        viewModel.testLiveData.observe(viewLifecycleOwner){
            databinding.mainFragmentHeaderTv.text = "Son ${it.size}"
            adapter.addItems(it)
        }

        viewModel.loadingStateLiveData.observe(viewLifecycleOwner){
            databinding.mainFragmentProgress.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun setListeners() {
        databinding.mainFragmentCta.setOnClickListener { viewModel.test() }
    }
}