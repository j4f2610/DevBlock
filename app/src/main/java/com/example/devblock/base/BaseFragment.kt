package com.example.devblock.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.devblock.R
import java.lang.reflect.ParameterizedType

/**
 * Created by Viktor on 01,June,2020
 */

open class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(@LayoutRes val layoutId: Int) :
    Fragment(), UIBehaviour {
    lateinit var binding: B
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController
    lateinit var mViewModel: VM
    lateinit var viewModelClass: Class<VM>

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            layoutId,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        viewModelClass = (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[1] as Class<VM>
        mViewModel = ViewModelProvider(
            this@BaseFragment,
            defaultViewModelProviderFactory
        ).get(viewModelClass)
        navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        registerBackPress()
        fetchData()
        bindData()
    }

    override fun getTAG(): String {
        return this.javaClass.name
    }

    override fun fetchData() {

    }

    override fun bindData() {

    }

    protected open fun doOnBackPressed() {}

    protected open fun enableOnBackPressedCallback() = false

    private fun registerBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(
                enableOnBackPressedCallback()
            ) {
                override fun handleOnBackPressed() {
                    doOnBackPressed()
                }
            })
    }
}

interface UIBehaviour {
    fun getTAG(): String
    fun fetchData()
    fun bindData()
}