package com.ddd.attendance.core.domain.repository

import com.ddd.attendance.core.model.member.MemberAttendance
import kotlinx.coroutines.flow.Flow

interface MemberRepository {
    fun getMemberAttendance(memberId: String): Flow<MemberAttendance>
}