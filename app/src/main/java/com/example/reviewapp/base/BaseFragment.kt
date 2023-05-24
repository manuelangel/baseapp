package com.example.reviewapp.base

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T: ViewDataBinding>: Fragment()  {

    protected lateinit var databinding: T

    protected abstract val layoutId : Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<T>(inflater, layoutId, container, false)
            .also { databinding = it }
            .root
    }

    protected fun showAlertDialog(dialogText:String,
                                  positiveButtonText:String,
                                  positiveButtonAction: DialogInterface.OnClickListener,
                                  negativeButtonText:String?=null,
                                  negativeButtonAction: DialogInterface.OnClickListener?=null){

        AlertDialog.Builder(requireContext()).apply {
            setTitle(dialogText)
            setPositiveButton(positiveButtonText, positiveButtonAction)
            if(negativeButtonAction != null && negativeButtonText != null){
                setNegativeButton(negativeButtonText, negativeButtonAction)
            }
        }.show()
    }
}