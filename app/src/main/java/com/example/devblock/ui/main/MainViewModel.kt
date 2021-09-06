package com.example.devblock.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.devblock.base.BaseViewModel
import com.example.devblock.data.model.ContactInfo
import com.example.devblock.data.network.repository.IAPIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mAPIRepository: IAPIRepository) :
    BaseViewModel() {

    private val _contactInfo = MutableLiveData<PagingData<ContactInfo>>()
    val contactInfo: LiveData<PagingData<ContactInfo>> = _contactInfo

    fun getContact(): Flow<PagingData<ContactInfo>> {
        return mAPIRepository
            .getContact()
            .cachedIn(viewModelScope)
    }
}
