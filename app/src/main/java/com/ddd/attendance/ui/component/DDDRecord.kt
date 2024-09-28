package com.ddd.attendance.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.attendance.model.AttendanceStatus
import com.ddd.attendance.ui.theme.DDD_200
import com.ddd.attendance.ui.theme.DDD_600
import com.ddd.attendance.ui.theme.DDD_800
import com.ddd.attendance.ui.theme.DDD_BLACK

@Composable
fun DDDRecord(
    sectionName: String,
    attendanceStatus: AttendanceStatus,
    date: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(size = 8.dp))
            .fillMaxWidth()
            .height(height = 56.dp)
            .background(
                color = when (attendanceStatus) {
                    AttendanceStatus.ATTENDANCE,
                    AttendanceStatus.TARDY -> DDD_200

                    AttendanceStatus.ABSENT -> DDD_800
                }
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        DDDText(
            text = sectionName,
            color = getDDDTextColorBy(attendanceStatus = attendanceStatus),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        DDDText(
            text = "/ ${attendanceStatus.textName}",
            modifier = Modifier.padding(start = 4.dp),
            color = DDD_600,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.weight(weight = 1f))
        DDDText(
            text = date,
            color = getDDDTextColorBy(attendanceStatus = attendanceStatus),
            fontWeight = FontWeight.W500,
            fontSize = 16.sp
        )
    }
}

private fun getDDDTextColorBy(attendanceStatus: AttendanceStatus): Color {
    return when (attendanceStatus) {
        AttendanceStatus.ATTENDANCE,
        AttendanceStatus.TARDY -> DDD_BLACK

        AttendanceStatus.ABSENT -> DDD_600
    }
}

@Preview(name = "출석 상태")
@Composable
private fun P1() {
    DDDRecord(
        sectionName = "직군 세션 1",
        attendanceStatus = AttendanceStatus.ATTENDANCE,
        date = "2024. 06. 24"
    )
}

@Preview(name = "지각 상태")
@Composable
private fun P2() {
    DDDRecord(
        sectionName = "직군 세션 2",
        attendanceStatus = AttendanceStatus.TARDY,
        date = "2024. 07. 24"
    )
}

@Preview(name = "결석 상태")
@Composable
private fun P3() {
    DDDRecord(
        sectionName = "부스팅 데이",
        attendanceStatus = AttendanceStatus.ABSENT,
        date = "2024. 08. 24"
    )
}