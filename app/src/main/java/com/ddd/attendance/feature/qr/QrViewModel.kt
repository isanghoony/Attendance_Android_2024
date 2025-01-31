package com.ddd.attendance.feature.qr

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QrViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

}