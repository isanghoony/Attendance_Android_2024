package com.ddd.attendance.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.attendance.R
import com.ddd.attendance.ui.theme.DDD_400

@Composable
fun DDDTopBar(
    modifier: Modifier = Modifier,
    type: TopBarType = TopBarType.LEFT_IMAGE,
    @DrawableRes rightImageResource: Int = 0,
    rightText: String = "",
    drawableResourceList: List<Int> = emptyList()
) {
    when (type) {
        TopBarType.LEFT_IMAGE -> DDDTopBar(
            isVisibleRightImage = false,
            modifier = modifier
        )

        TopBarType.LEFT_RIGHT_IMAGE -> DDDTopBar(
            modifier = modifier,
            isVisibleRightImage = true,
            rightImageResource = rightImageResource
        )

        TopBarType.LEFT_IMAGE_RIGHT_TEXT -> TextTopBar(
            text = rightText,
            modifier = modifier
        )

        TopBarType.IMAGE -> NonBackButtonTopBar(
            drawableResourceList = drawableResourceList,
            modifier = modifier
        )
    }
}

@Composable
private fun DDDTopBar(
    isVisibleRightImage: Boolean,
    modifier: Modifier = Modifier,
    @DrawableRes rightImageResource: Int = 0
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height = 56.dp)
            .padding(all = 8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_40_back),
            contentDescription = null,
            modifier = Modifier.align(alignment = Alignment.CenterStart)
        )
        if (isVisibleRightImage) {
            Image(
                painter = painterResource(id = rightImageResource),
                contentDescription = null,
                modifier = Modifier.align(alignment = Alignment.CenterEnd)
            )
        }
    }
}

@Composable
private fun NonBackButtonTopBar(
    drawableResourceList: List<Int>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(height = 56.dp)
    ) {
        Spacer(modifier = Modifier.weight(weight = 1f))
        drawableResourceList.forEach {
            Image(
                painter = painterResource(id = drawableResourceList[it]),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun TextTopBar(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height = 56.dp)
            .padding(all = 8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_40_back),
            contentDescription = null,
            modifier = Modifier.align(alignment = Alignment.CenterStart)
        )
        DDDText(
            text = text,
            modifier = Modifier.align(alignment = Alignment.CenterEnd),
            color = DDD_400,
            fontSize = 16.sp,
            fontWeight = FontWeight.W500
        )
    }
}

enum class TopBarType {
    LEFT_IMAGE,
    LEFT_RIGHT_IMAGE,
    LEFT_IMAGE_RIGHT_TEXT,
    IMAGE
}

@Preview(name = "뒤로가기 버튼이 없는 NonBackButtonTopBar")
@Composable
private fun P1() {
    NonBackButtonTopBar(
        drawableResourceList = listOf(
            R.drawable.ic_40_back,
            R.drawable.ic_40_qr,
            R.drawable.ic_40_logo
        )
    )
}

@Preview(name = "텍스트가 있는 TextTopBar")
@Composable
private fun P2() {
    TextTopBar(text = "건너뛰기")
}

@Preview(name = "오른쪽 이미지가 있는 DDDTopBar")
@Composable
private fun P3() {
    DDDTopBar(isVisibleRightImage = true)
}

@Preview(name = "오른쪽 이미지가 없는 DDDTopBar")
@Composable
private fun P4() {
    DDDTopBar(isVisibleRightImage = false)
}