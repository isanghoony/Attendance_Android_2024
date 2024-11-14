package com.ddd.attendance.ui.component.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ddd.attendance.ui.theme.DDD_800

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

    }
}

@Preview(name = "현황")
@Composable
private fun P1() {
}