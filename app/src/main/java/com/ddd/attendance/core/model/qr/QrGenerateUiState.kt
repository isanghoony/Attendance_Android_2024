package com.ddd.attendance.core.model.qr

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
sealed interface QrGenerateUiState {

    @Immutable
    data object Loading : QrGenerateUiState

    @Immutable
    data class Success(val qrString: String) : QrGenerateUiState

    @Immutable
    data class Error(val message: String) : QrGenerateUiState
}
