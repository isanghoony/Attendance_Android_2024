package com.ddd.attendance.ui.component.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.attendance.R
import com.ddd.attendance.ui.component.DDDText
import com.ddd.attendance.ui.theme.DDD_NEUTRAL_ORANGE_40
import com.ddd.attendance.ui.theme.DDD_WHITE

@Composable
fun DDDToolTip(
    modifier: Modifier = Modifier,
    text: String,
    cornerRadius: Dp = 12.dp,
    backgroundColor: Color = DDD_NEUTRAL_ORANGE_40,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
    ) {
        Card(
            shape = RoundedCornerShape(cornerRadius),
            elevation = cardElevation(0.dp),
            colors = CardDefaults.cardColors(
                containerColor = backgroundColor
            )
        ) {
            DDDText(
                modifier = Modifier.padding(
                    start = 12.dp,
                    top = 8.dp,
                    end = 12.dp,
                    bottom = 8.dp
                ),
                text = text,
                color = DDD_WHITE,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }

        Image(
            modifier = modifier.align(Alignment.CenterHorizontally),
            painter = painterResource(R.drawable.ic_tooltip_polygon),
            contentDescription = "Button Icon",
        )
    }
}

@Preview(name = "QR 안내 툴팁")
@Composable
private fun DDDToolTipPreview() {
    DDDToolTip(
        text = "Sample"
    ) { }
}