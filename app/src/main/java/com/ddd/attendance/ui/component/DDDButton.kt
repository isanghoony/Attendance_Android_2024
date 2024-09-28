package com.ddd.attendance.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.attendance.ui.theme.DDD_400
import com.ddd.attendance.ui.theme.DDD_600
import com.ddd.attendance.ui.theme.DDD_BLACK
import com.ddd.attendance.ui.theme.DDD_WHITE

@Composable
fun DDDButton(
    text: String,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .height(height = 56.dp)
            .background(
                color = if (isEnabled) {
                    DDD_WHITE
                } else {
                    DDD_600
                }
            )
            .clickable {
                if (isEnabled) {
                    onClick()
                }
            },
        contentAlignment = Alignment.Center
    ) {
        DDDText(
            text = text,
            color = if (isEnabled) {
                DDD_BLACK
            } else {
                DDD_400
            },
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(name = "활성화 된 버튼", widthDp = 300)
@Composable
private fun P1() {
    DDDButton(text = "다음")
}

@Preview(name = "비활성화 된 버튼", widthDp = 300)
@Composable
private fun P2() {
    DDDButton(text = "다음", isEnabled = false)
}