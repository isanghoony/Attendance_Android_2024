package com.ddd.attendance.core.model.qr.response

import kotlinx.serialization.SerialName

data class QrEncode(
    @SerialName("qr_string") val qrString: String
)