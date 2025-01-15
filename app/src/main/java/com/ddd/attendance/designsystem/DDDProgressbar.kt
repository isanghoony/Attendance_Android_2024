package com.ddd.attendance.designsystem

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ddd.attendance.ui.theme.DDD_NEUTRAL_GRAY_80
import com.ddd.attendance.ui.theme.DDD_WHITE

@Composable
fun DDDProgressbar(
    modifier: Modifier = Modifier,
    current: Int = 1,
    counts: Int = 3,
) {
    var composableWidth by remember {
        mutableIntStateOf(0)
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .onGloballyPositioned { layoutCoordinates ->
                composableWidth = layoutCoordinates.size.width
            }
    ) {
        Canvas(modifier = modifier) {
            val spacer = 2.dp.toPx()
            val width = (composableWidth - spacer * 2) / 3

            repeat(counts) { index ->
                drawRoundRect(
                    color = if (index < current) DDD_WHITE else DDD_NEUTRAL_GRAY_80,
                    topLeft = Offset(width * index + spacer * index, 0f),
                    size = Size(width = width, height = 3.dp.toPx()),
                    cornerRadius = CornerRadius(x = 99.dp.value, y = 99.dp.value)
                )
            }
        }
    }

}

@Composable
@Preview
fun DDDProgressbarPreview() {
    DDDProgressbar()
}