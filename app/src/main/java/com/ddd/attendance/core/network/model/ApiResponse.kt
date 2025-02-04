package com.ddd.attendance.core.network.model

data class ApiResponse<T>(
    val code: Int,
    val message: String,
    val data: T?
)