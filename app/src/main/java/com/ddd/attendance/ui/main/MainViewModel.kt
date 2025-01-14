package com.ddd.attendance.ui.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _isPermissionRequested = MutableStateFlow(false)
    val isPermissionRequested: StateFlow<Boolean> = _isPermissionRequested

    fun setPermissionRequested(value: Boolean) {
        _isPermissionRequested.value = value
    }
}