package com.example.devblock.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.example.devblock.ultis.ext.activityBinding

/**
 * Created by Viktor on 31,May,2020
 */

open class BaseActivity<B : ViewDataBinding>(@LayoutRes val layoutId: Int) : AppCompatActivity(),
    ActivityAction {

    private val binding: B by activityBinding(layoutId)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        initView()
        initData()
    }

    override fun initView() {

    }

    override fun initData() {

    }
}

interface ActivityAction {
    fun initView()
    fun initData()
}