package com.example.devblock.data.model

data class ListResponse<out T>(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: T
)