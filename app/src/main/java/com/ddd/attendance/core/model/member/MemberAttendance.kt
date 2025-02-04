package com.ddd.attendance.core.model.member

data class MemberAttendance(
    val memberId: String,
    val totalAttendance: Int,
    val lateCount: Int,
    val absentCount: Int,
    val attendanceRecords: List<MemberAttendanceRecord>
)
data class MemberAttendanceRecord(val test: String)