package com.task.noteapp.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.task.noteapp.R
import com.task.noteapp.databinding.ActivityBaseBinding

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {

    private var baseViewModel: BaseViewModel? = null

    abstract fun getViewModel(): BaseViewModel

    var viewBinding: VB? = null

    abstract var layoutId: Int

    var activityBaseBinding: ActivityBaseBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBaseBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_base
        )
        activityBaseBinding?.executePendingBindings()
        activityBaseBinding?.lifecycleOwner = this
        viewBinding = DataBindingUtil.inflate(
            layoutInflater,
            layoutId,
            activityBaseBinding?.layoutContainer,
            false
        )
        activityBaseBinding?.layoutContainer?.addView(viewBinding?.root)
        baseViewModel = getViewModel()
    }
}