package com.ddd.attendance.core.domain.repository

import com.ddd.attendance.core.model.qr.QrDecode
import com.ddd.attendance.core.model.qr.QrEncode

interface QrRepository {
    suspend fun qrEncode(userId: String): QrEncode
    suspend fun qrDecode(qrString: String): QrDecode
}