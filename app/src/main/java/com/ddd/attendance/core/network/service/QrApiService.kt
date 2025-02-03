package com.ddd.attendance.core.network.service

import com.ddd.attendance.core.model.qr.response.QrDecode
import com.ddd.attendance.core.model.qr.response.QrEncode
import com.ddd.attendance.core.model.qr.request.QrDecodeRequest
import com.ddd.attendance.core.model.qr.request.QrEncodeRequest
import com.ddd.attendance.core.network.response.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface QrApiService {
    /**
     * ### QR 문자열 생성
     * 사용자 ID 및 타임스탬프를 포함하여, QR로 인코딩할 문자열을 생성
     */
    @POST("qr/encode")
    suspend fun qrEncode(
        @Body request: QrEncodeRequest
    ): ApiResponse<QrEncode>

    /**
     * ### QR 문자열 디코딩/검증
     * QR 문자열을 디코딩한 뒤, 포함된 시간 정보가 유효한지(만료 등)를 검증
     */
    @POST("qr/decode")
    suspend fun qrDecode(
        /*@Header("Authorization") token: String,  // Bearer 토큰 포함*/
        @Body request: QrDecodeRequest
    ): ApiResponse<QrDecode>
}