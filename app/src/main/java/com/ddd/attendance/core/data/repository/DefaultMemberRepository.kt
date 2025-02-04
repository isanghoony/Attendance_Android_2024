package com.ddd.attendance.core.data.repository

import com.ddd.attendance.core.data.mapper.member.toData
import com.ddd.attendance.core.domain.repository.MemberRepository
import com.ddd.attendance.core.model.member.MemberAttendance
import com.ddd.attendance.core.network.service.MemberApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DefaultMemberRepository(
    private val api: MemberApiService
): MemberRepository {
    override fun getMemberAttendance(memberId: String): Flow<MemberAttendance> = flow {
        api.getMemberAttendance(memberId).data?.let {
            emit(it.toData())
        }
    }
}