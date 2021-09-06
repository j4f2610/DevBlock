package com.example.devblock.data.network.api

import com.example.devblock.data.model.ContactInfo
import com.example.devblock.data.model.ListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

@JvmSuppressWildcards
interface APIService {

    @GET("users")
    fun getContact(
        @Query("page") page: Int,
    ): Single<ListResponse<List<ContactInfo>>>
}