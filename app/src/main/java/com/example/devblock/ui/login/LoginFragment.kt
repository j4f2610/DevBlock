package com.example.devblock.ui.login

import com.example.devblock.R
import com.example.devblock.base.BaseFragment
import com.example.devblock.databinding.LoginFragmentBinding
import com.example.devblock.ultis.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginFragmentBinding, LoginViewModel>(R.layout.login_fragment) {
    override fun bindData() {
        super.bindData()
        binding.loginViewModel = mViewModel
        binding.btnLogin.setOnClickListener {
            mViewModel.login(binding.edtUserName.text.toString(), binding.edtPassword.text.toString())
        }
        mViewModel.userInfo.observe(viewLifecycleOwner) {
            if (it.userName == "" && it.password == "") {
                binding.llLogin.showSnackBar(getString(R.string.title_login_fail))
            }else{
                navController.navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment(userInfo = it))
            }
        }
    }
}