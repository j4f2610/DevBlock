package com.example.devblock

import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.devblock.base.BaseActivity
import com.example.devblock.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding>(R.layout.main_activity) {
    override fun initView() {
        super.initView()
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    fun enableBack(isShow: Boolean) {
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(isShow)
            setHomeButtonEnabled(isShow);
        }
    }
}