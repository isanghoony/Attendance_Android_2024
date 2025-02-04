package com.ddd.attendance.feature.member

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.attendance.core.domain.usecase.GetMemberAttendanceUseCase
import com.ddd.attendance.core.model.member.MemberAttendanceUiState
import com.ddd.attendance.core.utils.default
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemberViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val memberAttendanceUseCase: GetMemberAttendanceUseCase
) : ViewModel() {
    private val _attendanceUiState = MutableStateFlow<MemberAttendanceUiState>(MemberAttendanceUiState.Loading)
    val attendanceUiState = _attendanceUiState.asStateFlow()

    fun getAttendanceList(memberId: String) {
        viewModelScope.launch {
            memberAttendanceUseCase(memberId)
                .map { data ->
                    if (data.attendanceRecords.isNotEmpty()) {
                        MemberAttendanceUiState.Success(data.attendanceRecords)
                    } else {
                        MemberAttendanceUiState.Empty
                    }
                }
                .catch { throwable ->
                    emit(MemberAttendanceUiState.Error(throwable.message.default()))
                }
                .collect { state ->
                    _attendanceUiState.value = state
                }
        }
    }
}