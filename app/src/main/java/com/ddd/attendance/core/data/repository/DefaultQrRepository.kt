package com.ddd.attendance.core.data.repository

import com.ddd.attendance.core.domain.repository.QrRepository
import com.ddd.attendance.core.model.qr.response.QrDecode
import com.ddd.attendance.core.model.qr.response.QrEncode
import com.ddd.attendance.core.model.qr.request.QrDecodeRequest
import com.ddd.attendance.core.model.qr.request.QrEncodeRequest
import com.ddd.attendance.core.network.service.QrApiService
import com.ddd.attendance.core.utils.default

//Mapper 생략 상태...
class DefaultQrRepository(
    private val api: QrApiService
): QrRepository {
    override suspend fun qrEncode(userId: String): QrEncode {
        val data = api.qrEncode(
            request = QrEncodeRequest(
                user_id = userId
            )
        ).data
        return QrEncode(
            qrString = data?.qrString.default()
        )
    }

    override suspend fun qrDecode(qrString: String): QrDecode {
        val data = api.qrDecode(
            request = QrDecodeRequest(
                qr_string = qrString
            )
        ).data
        return QrDecode(
            userId = data?.userId.default(),
            timestamp = data?.timestamp.default()
        )
    }
}