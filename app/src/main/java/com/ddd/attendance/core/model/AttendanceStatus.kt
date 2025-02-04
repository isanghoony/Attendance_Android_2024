package com.ddd.attendance.core.model

enum class AttendanceStatus(val textName: String) {
    ATTENDANCE(textName = "출석"),
    TARDY(textName = "지각"),
    ABSENT(textName = "결석")
}