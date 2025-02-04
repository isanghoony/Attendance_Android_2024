package com.ddd.attendance.core.network.model.qr

import kotlinx.serialization.SerialName

data class QrEncodeResponse(
    @SerialName("qr_string") val qrString: String
)