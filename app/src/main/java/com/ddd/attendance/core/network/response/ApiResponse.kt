package com.ddd.attendance.core.network.response

data class ApiResponse<T>(
    val code: Int,
    val message: String,
    val data: T?
)