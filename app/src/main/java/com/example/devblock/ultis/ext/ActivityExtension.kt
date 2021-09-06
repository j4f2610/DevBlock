package com.example.devblock.ultis.ext

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.devblock.ultis.ActivityBindingProperty

/**
 * Created by Viktor on 31,May,2020
 */
inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(
    viewModelFactory: ViewModelProvider.Factory
): T {
    return ViewModelProvider(this, viewModelFactory).get(T::class.java)
}

fun <T : ViewDataBinding> activityBinding(@LayoutRes resId: Int) =
    ActivityBindingProperty<T>(resId)