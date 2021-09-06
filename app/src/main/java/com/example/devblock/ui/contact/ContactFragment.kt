package com.example.devblock.ui.contact

import androidx.navigation.fragment.navArgs
import com.example.devblock.MainActivity
import com.example.devblock.R
import com.example.devblock.base.BaseFragment
import com.example.devblock.databinding.ContactFragmentBinding
import com.example.devblock.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactFragment :
    BaseFragment<ContactFragmentBinding, MainViewModel>(R.layout.contact_fragment) {
    private val params by navArgs<ContactFragmentArgs>()

    override fun bindData() {
        super.bindData()
        (activity as MainActivity).enableBack(true)
        binding.run {
            contactInfo = params.contactInfo
            btnUpdate.setOnClickListener {
                val contact = params.contactInfo
                contact.run {
                    first_name = binding.edtContactFirstName.text.toString()
                    last_name = binding.edtContactLastName.text.toString()
                    email = binding.edtContactEmail.text.toString()
                }
                mViewModel.updateContactInfo(contact)
                (activity as MainActivity).onBackPressed()
            }
        }
    }
}