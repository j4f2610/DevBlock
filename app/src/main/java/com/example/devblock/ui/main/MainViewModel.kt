package com.example.devblock.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.devblock.base.BaseViewModel
import com.example.devblock.data.model.ContactInfo
import com.example.devblock.data.network.repository.IAPIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mAPIRepository: IAPIRepository) :
    BaseViewModel() {

    private var _updateContactInfo = mAPIRepository
        .getContact()
        .cachedIn(viewModelScope).let { it as MutableLiveData<PagingData<ContactInfo>> }
    val updateContactInfo: LiveData<PagingData<ContactInfo>> = _updateContactInfo

    fun updateContactInfo(contactInfo: ContactInfo) {
        val pagingData = _updateContactInfo.value ?: return
        pagingData.map {
            if (contactInfo.id == it.id) return@map it.copy(
                first_name = it.first_name,
                last_name = it.last_name,
                email = it.email
            )
            else return@map it
        }
            .let { _updateContactInfo.value = it }
    }
}
