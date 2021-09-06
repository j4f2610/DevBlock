package com.example.devblock.ui.main

import androidx.navigation.fragment.navArgs
import com.example.devblock.MainActivity
import com.example.devblock.R
import com.example.devblock.base.BaseFragment
import com.example.devblock.data.model.ContactInfo
import com.example.devblock.databinding.MainFragmentBinding
import com.example.devblock.ui.main.adapter.ContactsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<MainFragmentBinding, MainViewModel>(R.layout.main_fragment) {

    lateinit var mContactsAdapter: ContactsAdapter
    private val params by navArgs<MainFragmentArgs>()

    override fun bindData() {
        super.bindData()
        (activity as MainActivity).enableBack(false)
        binding.userInfo = params.userInfo
        mContactsAdapter = ContactsAdapter(object : ContactsAdapter.ContactOnClickListener {
            override fun onItemClick(contactInfo: ContactInfo) {
                navController.navigate(
                    MainFragmentDirections.actionMainFragmentToContactFragment(
                        contactInfo
                    )
                )
            }

        })
        binding.rcvContacts.adapter = mContactsAdapter
        mViewModel.updateContactInfo.observe(viewLifecycleOwner, { pagingData ->
            if (pagingData != null) {
                mContactsAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
            }
        })

    }
}