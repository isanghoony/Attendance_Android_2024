package com.ddd.attendance.core.domain.usecase

import com.ddd.attendance.core.domain.repository.QrRepository
import com.ddd.attendance.core.model.qr.QrEncode

class QrEncodeUseCase(private val repository: QrRepository) {
    suspend operator fun invoke(userId: String): QrEncode {
        return repository.qrEncode(userId)
    }
}