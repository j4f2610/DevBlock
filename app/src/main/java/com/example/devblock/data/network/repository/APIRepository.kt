package com.example.devblock.data.network.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.devblock.data.data_source.ContactPagingSource
import com.example.devblock.data.model.ContactInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class APIRepository @Inject constructor(private val mContactPagingSource: ContactPagingSource) :
    IAPIRepository {
    override fun getContact(): LiveData<PagingData<ContactInfo>> =
        Pager(config = PagingConfig(enablePlaceholders = false, pageSize = 6),
            pagingSourceFactory = { mContactPagingSource }
        ).liveData
}

interface IAPIRepository {
    fun getContact(): LiveData<PagingData<ContactInfo>>
}
