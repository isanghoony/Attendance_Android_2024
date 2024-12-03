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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
fun DDDMemberSituation(
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
            AttendanceStatusRow(
                modifier = Modifier,
                text = stringResource(R.string.attendance_status),
                textColor = DDD_BORDER_INACTIVE,
                icon = painterResource(R.drawable.ic_arrow_gray)
            )

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                DDDMemberSituationItem(
                    count = attendanceCount,
                    label = stringResource(R.string.member_attendance),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

                Spacer(Modifier.weight(1f))

                DDDMemberSituationItem(
                    count = tardyCount,
                    label = stringResource(R.string.member_tardy),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

                Spacer(Modifier.weight(1f))

                DDDMemberSituationItem(
                    count = absentCount,
                    label = stringResource(R.string.member_absent),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }
}

@Composable
fun DDDAdminSituation(
    modifier: Modifier = Modifier
) {

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DDDAdminSituationItem(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.attendance_status),
                icon = painterResource(R.drawable.ic_attendance_stamp),
            )

            Spacer(Modifier.width(12.dp))

            DDDAdminSituationItem(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.qr_code),
                icon = painterResource(R.drawable.ic_attendance_stamp),
            )
        }

        Spacer(Modifier.height(12.dp))

        Box(
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .background(DDD_NEUTRAL_GRAY_80),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 20.dp, top = 16.dp, end = 20.dp, bottom = 20.dp)
            ) {
                AttendanceStatusRow(
                    text = stringResource(R.string.schedule),
                    textColor = DDD_WHITE,
                    icon = painterResource(R.drawable.ic_arrow_white)
                )
            }
        }
    }
}

@Composable
fun AttendanceStatusRow(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    icon: Painter
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        DDDText(
            text = text,
            color = textColor,
            fontWeight = FontWeight.W500,
            fontSize = 18.sp
        )

        Image(
            painter = icon,
            contentDescription = "Arrow Icon",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun DDDMemberSituationItem(
    modifier: Modifier = Modifier,
    count: Int,
    label: String
) {
    Column(
        modifier = modifier.width(68.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        DDDText(
            text = "$count",
            textStyle = MaterialTheme.typography.headlineSmall,
            color = DDD_WHITE,
            fontWeight = FontWeight.W700,
        )
        Spacer(modifier = Modifier.height(4.dp))

        DDDText(
            text = label,
            textStyle = MaterialTheme.typography.bodyMedium,
            color = DDD_BORDER_INACTIVE,
            fontWeight = FontWeight.W500,
        )
    }
}

@Composable
fun DDDAdminSituationItem(
    modifier: Modifier = Modifier,
    text: String,
    icon: Painter
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(DDD_NEUTRAL_GRAY_80),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 20.dp)
        ) {
            Row (modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically) {
                DDDText(
                    text = text,
                    color = DDD_WHITE,
                    fontWeight = FontWeight.W700,
                    fontSize = 20.sp
                )

                Spacer(Modifier.width(12.dp))

                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.ic_arrow_white),
                    contentDescription = "Arrow Icon"
                )
            }

            Spacer(Modifier.height(12.dp))

            Image(
                modifier = Modifier.align(Alignment.End),
                painter = icon,
                contentDescription = "Arrow Icon",
            )
        }

    }
}

@Preview(name = "공통 카드 타이틀")
@Composable
private fun P1() {
    AttendanceStatusRow(
        text = stringResource(R.string.attendance_status),
        textColor = DDD_BORDER_INACTIVE,
        icon = painterResource(R.drawable.ic_arrow_gray)
    )
}

@Preview(name = "멤버 아이템 카드")
@Composable
private fun P2() {
    DDDMemberSituationItem(
        count = 3,
        label = "출석"
    )
}

@Preview(name = "멤버 현황")
@Composable
private fun P3() {
    DDDMemberSituation(
        attendanceCount = 2,
        tardyCount = 3,
        absentCount = 5
    )
}

@Preview(name = "운영진 아이템 카드")
@Composable
private fun P4() {
    DDDAdminSituationItem(
        text = stringResource(R.string.attendance_status),
        icon = painterResource(R.drawable.ic_attendance_stamp)
    )
}

@Preview(name = "운영진 현황")
@Composable
private fun P5() {
    DDDAdminSituation()
}