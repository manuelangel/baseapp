package com.example.reviewapp.base

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T:ViewDataBinding> : AppCompatActivity(){

    protected lateinit var databinding: T

    protected abstract val layoutId : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = DataBindingUtil.setContentView(this, layoutId)
    }

    protected fun showAlertDialog(dialogText:String,
                                  positiveButtonText:String,
                                  positiveButtonAction: DialogInterface.OnClickListener,
                                  negativeButtonText:String?=null,
                                  negativeButtonAction: DialogInterface.OnClickListener?=null){

        AlertDialog.Builder(this).apply {
            setTitle(dialogText)
            setPositiveButton(positiveButtonText, positiveButtonAction)
            if(negativeButtonAction != null && negativeButtonText != null){
                setNegativeButton(negativeButtonText, negativeButtonAction)
            }
        }.show()
    }

}