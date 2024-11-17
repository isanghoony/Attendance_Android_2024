package com.ddd.attendance.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DDDIconWithText(
    modifier: Modifier = Modifier,
    image: Int,
    spacing: Dp = 2.dp,
    textComposable: @Composable () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Image(
            modifier = Modifier.size(20.dp),
            painter = painterResource(image),
            contentDescription = "vector icon",

        )

        Spacer(modifier = Modifier.width(spacing))

        textComposable()
    }
}