package com.ddd.attendance.core.network.model.qr

import kotlinx.serialization.SerialName

data class QrDecodeResponse(
    @SerialName("user_id") val userId: String,
    @SerialName("timestamp") val timestamp: String
)