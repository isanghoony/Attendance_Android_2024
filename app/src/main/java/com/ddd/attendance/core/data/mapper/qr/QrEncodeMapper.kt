package com.ddd.attendance.core.data.mapper.qr

import com.ddd.attendance.core.model.qr.QrEncode
import com.ddd.attendance.core.network.model.qr.QrEncodeResponse

internal fun QrEncodeResponse?.toData(): QrEncode {
    return this?.let {
        QrEncode(
            qrString = qrString
        )
    } ?: run {
        QrEncode(
            qrString = ""
        )
    }
}