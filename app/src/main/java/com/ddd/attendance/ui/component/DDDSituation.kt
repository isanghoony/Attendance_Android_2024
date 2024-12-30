package com.ddd.attendance.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.attendance.R
import com.ddd.attendance.ui.theme.DDD_BLACK_TEXT_PRIMARY
import com.ddd.attendance.ui.theme.DDD_BLACK_TEXT_SECONDARY
import com.ddd.attendance.ui.theme.DDD_BORDER_ALTERNATIVE
import com.ddd.attendance.ui.theme.DDD_GRAY_F5
import com.ddd.attendance.ui.theme.DDD_NEUTRAL_GRAY_20
import com.ddd.attendance.ui.theme.DDD_NEUTRAL_GRAY_80
import com.ddd.attendance.ui.theme.DDD_WHITE
import com.ddd.attendance.ui.theme.DDD_WHITE_TEXT_PRIMARY
import com.ddd.attendance.ui.theme.DDD_WHITE_TEXT_SECONDARY
import com.ddd.attendance.utils.noRippleClickable

@Composable
fun DDDMemberSituation(
    modifier: Modifier = Modifier,
    radius: Dp = 20.dp,
    attendanceCount: Int,
    tardyCount: Int,
    absentCount: Int
) {
    val backgroundColor = if (isSystemInDarkTheme()) DDD_BORDER_ALTERNATIVE else DDD_GRAY_F5
    val dividerColor = if (isSystemInDarkTheme()) DDD_NEUTRAL_GRAY_80 else DDD_NEUTRAL_GRAY_20

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(radius))
            .background(backgroundColor)
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DDDMemberSituationItem(count = attendanceCount, label = stringResource(R.string.member_attendance))

            Spacer(Modifier.weight(1f))
            Spacer(
                Modifier
                    .height(48.dp)
                    .width(1.dp)
                    .background(dividerColor)
            )
            Spacer(Modifier.weight(1f))

            DDDMemberSituationItem(count = tardyCount, label = stringResource(R.string.member_tardy))

            Spacer(Modifier.weight(1f))
            Spacer(
                Modifier
                    .height(48.dp)
                    .width(1.dp)
                    .background(dividerColor)
            )
            Spacer(Modifier.weight(1f))

            DDDMemberSituationItem(count = absentCount, label = stringResource(R.string.member_absent))
        }
    }
}

@Composable
fun AttendanceStatusRow(
    modifier: Modifier = Modifier,
    onPressQrcode: () -> Unit,
    onPressMyPage: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_44_logo_black),
            contentDescription = "Logo Icon"
        )
        Spacer(Modifier.weight(1f))
        ActionIcon(painterResource(R.drawable.ic_36_qr_code), onPressQrcode)
        Spacer(Modifier.width(12.dp))
        ActionIcon(painterResource(R.drawable.ic_36_my_info), onPressMyPage)
    }
}

@Composable
private fun ActionIcon(icon: Painter, onClick: () -> Unit) {
    Image(
        modifier = Modifier
            .size(36.dp)
            .noRippleClickable(onClick = onClick),
        painter = icon,
        contentDescription = null
    )
}

@Composable
fun DDDMemberSituationItem(
    modifier: Modifier = Modifier,
    count: Int,
    label: String
) {
    val primaryColor = if (isSystemInDarkTheme()) DDD_BLACK_TEXT_PRIMARY else DDD_WHITE_TEXT_PRIMARY
    val secondaryColor = if (isSystemInDarkTheme()) DDD_BLACK_TEXT_SECONDARY else DDD_WHITE_TEXT_SECONDARY

    Column(
        modifier = modifier.width(68.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DDDText(
            text = "$count",
            color = primaryColor,
            textStyle = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(4.dp))
        DDDText(
            text = label,
            color = secondaryColor,
            textStyle = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun DDDAdminSituationItem(
    modifier: Modifier = Modifier,
    text: String,
    icon: Painter
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(DDD_NEUTRAL_GRAY_80),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                DDDText(
                    text = text,
                    color = DDD_WHITE,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(Modifier.width(12.dp))
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.ic_login_logo),
                    contentDescription = null
                )
            }
            Spacer(Modifier.height(12.dp))
            Image(
                modifier = Modifier.align(Alignment.End),
                painter = icon,
                contentDescription = null
            )
        }
    }
}

@Preview(name = "공통 카드 타이틀")
@Composable
private fun P1() {
    AttendanceStatusRow(
        onPressQrcode = {},
        onPressMyPage = {}
    )
}

@Preview(name = "현황 카드")
@Composable
private fun P2() {
    DDDMemberSituationItem(
        count = 2,
        label = stringResource(R.string.member_absent)
    )
}

@Preview(name = "현황", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun P3_BLACK() {
    DDDMemberSituation(
        attendanceCount = 2,
        tardyCount = 3,
        absentCount = 5
    )
}

@Preview(name = "현황", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun P3_WHITE() {
    DDDMemberSituation(
        attendanceCount = 2,
        tardyCount = 3,
        absentCount = 5
    )
}