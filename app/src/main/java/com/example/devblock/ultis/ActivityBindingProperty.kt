package com.example.devblock.ultis

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by Viktor on 31,May,2020
 */
class ActivityBindingProperty<out T : ViewDataBinding>(@LayoutRes private val resId: Int) :
    ReadOnlyProperty<Activity, T> {

    private var binding: T? = null

    override operator fun getValue(thisRef: Activity, property: KProperty<*>): T =
        binding ?: createBinding(thisRef).also { binding = it }

    private fun createBinding(activity: Activity): T =
        DataBindingUtil.setContentView(activity, resId)
}