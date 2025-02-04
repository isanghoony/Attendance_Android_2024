package com.ddd.attendance.core.designsystem

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
import com.ddd.attendance.core.model.JobRole
import com.ddd.attendance.core.ui.theme.DDD_200
import com.ddd.attendance.core.ui.theme.DDD_600
import com.ddd.attendance.core.ui.theme.DDD_800
import com.ddd.attendance.core.ui.theme.DDD_BLACK

@Composable
fun DDDRoster(
    name: String,
    jobRole: JobRole,
    batchCount: Int,
    isCurrentBatch: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(size = 8.dp))
            .fillMaxWidth()
            .height(height = 56.dp)
            .background(
                color = if (isCurrentBatch) {
                    DDD_200
                } else {
                    DDD_800
                }
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        DDDText(
            text = name,
            color = getDDDTextColorBy(isCurrentBatch = isCurrentBatch),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        DDDText(
            text = "/ ${batchCount}th",
            modifier = Modifier.padding(start = 4.dp),
            color = DDD_600,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.weight(weight = 1f))
        DDDText(
            text = jobRole.textName,
            color = getDDDTextColorBy(isCurrentBatch = isCurrentBatch),
            fontWeight = FontWeight.W500,
            fontSize = 16.sp
        )
    }
}

private fun getDDDTextColorBy(isCurrentBatch: Boolean): Color {
    return if (isCurrentBatch) {
        DDD_BLACK
    } else {
        DDD_600
    }
}

@Preview(name = "현재 기수가 11기 이고 Android 담당하는 name")
@Composable
private fun P1() {
    DDDRoster(
        name = "이름",
        jobRole = JobRole.ANDROID,
        batchCount = 11,
        isCurrentBatch = true
    )
}

@Preview(name = "현재 기수가 11기, 10기에 iOS를 담당했던 name")
@Composable
private fun P2() {
    DDDRoster(
        name = "이름",
        jobRole = JobRole.IOS,
        batchCount = 10,
        isCurrentBatch = false
    )
}