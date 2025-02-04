package com.ddd.attendance.core.data.mapper.qr

import com.ddd.attendance.core.model.qr.QrDecode
import com.ddd.attendance.core.network.model.qr.QrDecodeResponse

internal fun QrDecodeResponse?.toData(): QrDecode {
    return this?.let {
        QrDecode(
            userId = userId,
            timestamp = timestamp
        )
    } ?: QrDecode(
        userId = "",
        timestamp = ""
    )
}