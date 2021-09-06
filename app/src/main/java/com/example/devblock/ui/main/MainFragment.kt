package com.example.devblock.ui.main

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.devblock.R
import com.example.devblock.base.BaseFragment
import com.example.devblock.databinding.MainFragmentBinding
import com.example.devblock.ui.main.adapter.ContactsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<MainFragmentBinding, MainViewModel>(R.layout.main_fragment) {

    lateinit var mContactsAdapter: ContactsAdapter
    private val params by navArgs<MainFragmentArgs>()

    override fun bindData() {
        super.bindData()
        binding.userInfo = params.userInfo
        mContactsAdapter = ContactsAdapter()
        binding.rcvContacts.adapter = mContactsAdapter
        lifecycleScope.launch {
            mViewModel.getContact()
                .collectLatest {
                    mContactsAdapter.submitData(lifecycle, it)
                }
        }
    }
}