package com.example.devblock.data.data_source

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.devblock.data.model.ContactInfo
import com.example.devblock.data.model.ListResponse
import com.example.devblock.data.network.api.APIService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactPagingSource @Inject constructor(private val mAPIService: APIService) :
    RxPagingSource<Int, ContactInfo>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, ContactInfo>> {
        val position = params.key ?: 1
        return mAPIService.getContact(position).subscribeOn(Schedulers.io())
            .map { toLoadResult(it, position) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(
        data: ListResponse<List<ContactInfo>>,
        position: Int
    ): LoadResult<Int, ContactInfo> {
        return LoadResult.Page(
            data = data.data,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (position == data.total) null else position + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, ContactInfo>): Int? {
        return state.anchorPosition
    }
}