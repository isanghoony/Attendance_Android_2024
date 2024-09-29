package com.ddd.attendance.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import com.ddd.attendance.ui.component.DDDText
import com.ddd.attendance.ui.theme.DDD_300
import com.ddd.attendance.ui.theme.DDD_BLACK

@Composable
fun LoginScreen(
    onClickGoogle: () -> Unit
) {
    Content(onClickGoogle = onClickGoogle)
}

@Composable
private fun Content(
    onClickGoogle: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = DDD_BLACK)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_login_logo),
            contentDescription = null,
            modifier = Modifier.align(alignment = Alignment.Center)
        )
        DDDText(
            text = "Google로 계속하기",
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(bottom = 24.dp)
                .clickable(onClick = onClickGoogle),
            color = DDD_300,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp
        )
    }
}

@Preview(name = "Content")
@Composable
private fun P1() {
    Content{}
}