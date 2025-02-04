package com.ddd.attendance.core.data.mapper.member

import com.ddd.attendance.core.model.member.MemberAttendance
import com.ddd.attendance.core.model.member.MemberAttendanceRecord
import com.ddd.attendance.core.network.model.member.MemberAttendanceRecordResponse
import com.ddd.attendance.core.network.model.member.MemberAttendanceResponse

internal fun MemberAttendanceResponse.toData(): MemberAttendance =
    MemberAttendance(
        memberId = memberId,
        totalAttendance = totalAttendance,
        lateCount = lateCount,
        absentCount = absentCount,
        attendanceRecords = attendanceRecords.toData()
    )

internal fun List<MemberAttendanceRecordResponse>?.toData() : List<MemberAttendanceRecord> {
    return this?.map { response ->
        MemberAttendanceRecord(test = response.test)
    } ?: emptyList()
}
