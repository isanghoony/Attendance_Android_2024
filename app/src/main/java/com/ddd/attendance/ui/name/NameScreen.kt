package com.ddd.attendance.ui.name

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.attendance.R
import com.ddd.attendance.ui.component.DDDButton
import com.ddd.attendance.ui.component.DDDText
import com.ddd.attendance.ui.component.DDDTopBar
import com.ddd.attendance.ui.component.TopBarType
import com.ddd.attendance.ui.theme.DDD_400
import com.ddd.attendance.ui.theme.DDD_BLACK
import com.ddd.attendance.ui.theme.DDD_WHITE

@Composable
fun NameScreen(
    onClickBackButton: () -> Unit,
    onClickNext: () -> Unit
) {
    Content(
        onClickBackButton = onClickBackButton,
        onClickNext = onClickNext
    )
}

@Composable
private fun Content(
    onClickBackButton: () -> Unit,
    onClickNext: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = DDD_BLACK)
    ) {
        DDDTopBar(
            type = TopBarType.LEFT_IMAGE,
            onClickLeftImage = onClickBackButton
        )
        Spacer(modifier = Modifier.height(height = 54.dp))
        DDDText(
            text = "이름이 어떻게 되시나요?",
            modifier = Modifier.padding(
                start = 32.dp,
                top = 54.dp
            ),
            color = DDD_WHITE,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        DDDText(
            text = "본명으로 입력해주세요",
            modifier = Modifier.padding(
                start = 32.dp,
                top = 16.dp
            ),
            color = DDD_400,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp
        )
        DDDInputText(
            modifier = Modifier
                .padding(
                    start = 32.dp,
                    end = 32.dp,
                    top = 54.dp
                )
        )
        Spacer(modifier = Modifier.weight(weight = 1f))
        DDDButton(
            text = "다음",
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClickNext)
        )
    }
}

@Composable
private fun DDDInputText(
    modifier: Modifier = Modifier
) {
    var textFieldValue by remember { mutableStateOf(value = TextFieldValue()) }

    Box(modifier = modifier.fillMaxWidth()) {
        BasicTextField(
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
            },
            modifier = Modifier
                .align(alignment = Alignment.CenterStart)
                .height(height = 50.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_24_code_clear),
            contentDescription = null,
            modifier = Modifier.align(alignment = Alignment.CenterEnd)
        )
        HorizontalDivider(
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(top = 8.dp)
                .background(color = DDD_WHITE)
                .fillMaxWidth()
        )
    }
}

@Preview(name = "Content")
@Composable
private fun P1() {
    Content(
        onClickBackButton = {},
        onClickNext = {}
    )
}