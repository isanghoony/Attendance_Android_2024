package com.ddd.attendance.core.model.qr

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
sealed interface QrScanUiState {

    @Immutable
    data object Loading : QrScanUiState

    @Immutable
    data class Success(val userId: String, val timeStamp: String) : QrScanUiState

    @Immutable
    data class Error(val message: String) : QrScanUiState
}
