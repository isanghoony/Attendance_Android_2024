package com.ddd.attendance.ui.component.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.attendance.R
import com.ddd.attendance.ui.component.DDDText
import com.ddd.attendance.ui.theme.DDD_BORDER_INACTIVE
import com.ddd.attendance.ui.theme.DDD_NEUTRAL_GRAY_80
import com.ddd.attendance.ui.theme.DDD_NEUTRAL_GRAY_90
import com.ddd.attendance.ui.theme.DDD_WHITE

@Composable
fun DDDSituation(
    modifier: Modifier = Modifier,
    radius: Dp = 20.dp,
    attendanceCount: Int,
    tardyCount: Int,
    absentCount: Int
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(radius))
            .background(DDD_NEUTRAL_GRAY_90)
            .padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 20.dp)
    ) {
        Column {
            AttendanceStatusRow(modifier = Modifier)

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                DDDSituationItem(
                    image = painterResource(R.drawable.ic_check),
                    count = attendanceCount,
                    label = stringResource(R.string.member_attendance),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

                Spacer(Modifier.weight(1f))

                DDDSituationItem(
                    image = painterResource(R.drawable.ic_tardy),
                    count = tardyCount,
                    label = stringResource(R.string.member_tardy),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

                Spacer(Modifier.weight(1f))

                DDDSituationItem(
                    image = painterResource(R.drawable.ic_absent),
                    count = absentCount,
                    label = stringResource(R.string.member_absent),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }
}

@Composable
fun AttendanceStatusRow(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        DDDText(
            text = stringResource(R.string.member_attendance_status),
            color = DDD_BORDER_INACTIVE,
            fontWeight = FontWeight.W500,
            fontSize = 18.sp
        )

        Image(
            painter = painterResource(R.drawable.ic_arrow),
            contentDescription = "Arrow Icon",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun DDDSituationItem(
    image: Painter,
    count: Int,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = modifier
                .size(68.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(DDD_NEUTRAL_GRAY_80),
            contentAlignment = Alignment.Center
        ) {
            DDDText(
                text = stringResource(R.string.member_times, count),
                color = DDD_WHITE,
                fontWeight = FontWeight.W700,
                fontSize = 22.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            DDDText(
                text = label,
                color = DDD_BORDER_INACTIVE,
                fontWeight = FontWeight.W500,
                fontSize = 14.sp
            )
        }
    }
}

@Preview(name = "출석 현황 텍스트")
@Composable
fun P1() {
    AttendanceStatusRow()
}

@Preview(name = "출석 아이템")
@Composable
fun P2() {
    DDDSituationItem(
        image = painterResource(R.drawable.ic_check),
        count = 3,
        label = "출석"
    )
}

@Preview(name = "현황")
@Composable
private fun P3() {
    DDDSituation(
        attendanceCount = 2,
        tardyCount = 3,
        absentCount = 5
    )
}