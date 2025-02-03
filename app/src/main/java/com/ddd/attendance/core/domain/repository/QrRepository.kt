package com.ddd.attendance.core.domain.repository

import com.ddd.attendance.core.model.qr.response.QrDecode
import com.ddd.attendance.core.model.qr.response.QrEncode

interface QrRepository {
    suspend fun qrEncode(userId: String): QrEncode
    suspend fun qrDecode(qrString: String): QrDecode
}