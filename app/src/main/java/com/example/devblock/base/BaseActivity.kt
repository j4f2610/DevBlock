package com.example.devblock.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.example.devblock.ultis.ext.activityBinding

/**
 * Created by Viktor on 31,May,2020
 */

open class BaseActivity<B : ViewDataBinding>(@LayoutRes val layoutId: Int) : AppCompatActivity() {

    private val binding: B by activityBinding(layoutId)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
    }
}