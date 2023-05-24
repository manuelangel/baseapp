package com.example.reviewapp.features.welcome.ui

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import com.example.reviewapp.R
import com.example.reviewapp.base.BaseFragment
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
        setObservers()
        setListeners()
        initRv()
    }

    private fun initRv() {
        databinding.mainFragmentPhotosRv.adapter = adapter
    }

    private fun setObservers() {

        viewModel.photosLiveData.observe(viewLifecycleOwner){
            if(it.isSuccess){
                adapter.setItems(it.getOrDefault(ArrayList()))
            }
            else{
                showAlertDialog(getString(R.string.generic_error_message),getString(R.string.generic_alert_close_button), { _, _ ->  })
            }
        }

        viewModel.loadingStateLiveData.observe(viewLifecycleOwner){
            databinding.mainFragmentProgress.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun setListeners() {
        databinding.mainFragmentCta.setOnClickListener { viewModel.loadPhotos() }
    }
}