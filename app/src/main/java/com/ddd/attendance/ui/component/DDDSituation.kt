package com.ddd.attendance.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.attendance.ui.theme.DDD_400
import com.ddd.attendance.ui.theme.DDD_800
import com.ddd.attendance.ui.theme.DDD_WHITE

@Composable
fun DDDSituation(
    attendanceCount: Int,
    tardyCount: Int,
    absentCount: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(size = 16.dp))
            .fillMaxWidth()
            .height(height = 80.dp)
            .background(color = DDD_800),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SituationInfo(text = "출석", count = attendanceCount)
        DDDVerticalDivider()
        SituationInfo(text = "지각", count = tardyCount)
        DDDVerticalDivider()
        SituationInfo(text = "결석", count = absentCount)
    }
}

@Composable
private fun SituationInfo(
    text: String,
    count: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        DDDText(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
            color = DDD_400
        )
        DDDText(
            text = "${count}회",
            fontSize = 18.sp,
            fontWeight = FontWeight.W700,
            color = DDD_WHITE
        )
    }
}

@Composable
private fun DDDVerticalDivider() {
    VerticalDivider(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .height(height = 50.dp)
    )
}

@Preview(name = "구분선")
@Composable
private fun P1() {
    DDDVerticalDivider()
}

@Preview(name = "현황")
@Composable
private fun P2() {
    SituationInfo(text = "출석", count = 1)
}

@Preview(name = "출석 현황")
@Composable
private fun P3() {
    DDDSituation(attendanceCount = 1, tardyCount = 2, absentCount = 3)
}
