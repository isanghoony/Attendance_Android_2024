package com.ddd.attendance.ui.main.screen.member

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ddd.attendance.R
import com.ddd.attendance.model.Schedule
import com.ddd.attendance.ui.component.AttendanceStatusRow
import com.ddd.attendance.ui.component.DDDMemberSituation
import com.ddd.attendance.ui.component.DDDText
import com.ddd.attendance.ui.main.MainViewModel
import com.ddd.attendance.ui.theme.DDD_BLACK
import com.ddd.attendance.ui.theme.DDD_BLACK_TEXT_PRIMARY
import com.ddd.attendance.ui.theme.DDD_BLACK_TEXT_SECONDARY
import com.ddd.attendance.ui.theme.DDD_BORDER_ALTERNATIVE
import com.ddd.attendance.ui.theme.DDD_GRAY_F5
import com.ddd.attendance.ui.theme.DDD_NEUTRAL_BLUE_20
import com.ddd.attendance.ui.theme.DDD_NEUTRAL_GRAY_50
import com.ddd.attendance.ui.theme.DDD_WHITE
import com.ddd.attendance.ui.theme.DDD_WHITE_TEXT_PRIMARY
import com.ddd.attendance.ui.theme.DDD_WHITE_TEXT_SECONDARY

@Composable
fun MemberScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    Content(
        onPressMyPage = {},
        onPressQrcode = {},
        onClickBackButton = {
            navController.popBackStack()
        }
    )
}

@Composable
private fun Content(
    onPressMyPage: () -> Unit,
    onPressQrcode: () -> Unit,
    onClickBackButton: () -> Unit,
) {
    BackHandler { onClickBackButton() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) DDD_BLACK else DDD_WHITE)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            item { Spacer(Modifier.height(36.dp)); HeaderSection(onPressMyPage, onPressQrcode) }
            item { Spacer(Modifier.height(20.dp)); BodySection() }
            items(getSampleSchedules()) { schedule ->
                ScheduleItem(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    schedule = schedule
                )
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}

@Composable
private fun HeaderSection(
    onPressMyPage: () -> Unit,
    onPressQrcode: () -> Unit
) {
    AttendanceStatusRow(
        modifier = Modifier.padding(start = 16.dp, end = 24.dp),
        onPressQrcode = onPressMyPage,
        onPressMyPage = onPressQrcode
    )
}

@Composable
private fun BodySection() {
    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        SectionTitle(
            text = stringResource(R.string.member_attendance_status, "김디디"),
            style = MaterialTheme.typography.titleLarge,
            color = if (isSystemInDarkTheme()) DDD_BLACK_TEXT_PRIMARY else DDD_WHITE_TEXT_PRIMARY,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(16.dp))

        SectionText(
            text = stringResource(R.string.member_activity_period, "2025.03.12 ~ 2025.08.12"),
            color = if (isSystemInDarkTheme()) DDD_BLACK_TEXT_SECONDARY else DDD_WHITE_TEXT_SECONDARY,
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(Modifier.height(8.dp))
        DDDMemberSituation(attendanceCount = 2, tardyCount = 3, absentCount = 5)
        Spacer(Modifier.height(56.dp))

        SectionTitle(
            text = stringResource(R.string.member_th_schedule, "12"),
            style = MaterialTheme.typography.titleMedium,
            color = if (isSystemInDarkTheme()) DDD_BLACK_TEXT_PRIMARY else DDD_WHITE_TEXT_PRIMARY,
            fontWeight = FontWeight.Medium
        )

        Spacer(Modifier.height(16.dp))
    }
}

@Composable
private fun SectionTitle(
    text: String,
    style: androidx.compose.ui.text.TextStyle,
    color: androidx.compose.ui.graphics.Color,
    fontWeight: FontWeight
) {
    DDDText(text = text, textStyle = style, color = color, fontWeight = fontWeight)
}

@Composable
private fun SectionText(
    text: String,
    style: androidx.compose.ui.text.TextStyle,
    color: androidx.compose.ui.graphics.Color
) {
    DDDText(text = text, textStyle = style, color = color, fontWeight = FontWeight.Normal)
}

@Composable
private fun ScheduleItem(
    modifier: Modifier = Modifier,
    schedule: Schedule
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSystemInDarkTheme()) DDD_BORDER_ALTERNATIVE else DDD_GRAY_F5)
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ScheduleDateBox(month = schedule.month, day = schedule.day)
            Spacer(Modifier.width(12.dp))
            ScheduleDetails(title = schedule.title, content = schedule.content)
        }
    }
}

@Composable
private fun ScheduleDateBox(month: String, day: String) {
    Box(
        modifier = Modifier
            .size(54.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(DDD_NEUTRAL_BLUE_20),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            SectionText(
                text = month,
                style = MaterialTheme.typography.bodySmall,
                color = if (isSystemInDarkTheme()) DDD_BLACK else DDD_WHITE_TEXT_PRIMARY
            )
            Spacer(Modifier.height(4.dp))
            SectionText(
                text = day,
                style = MaterialTheme.typography.titleSmall,
                color = if (isSystemInDarkTheme()) DDD_BLACK else DDD_WHITE_TEXT_PRIMARY
            )
        }
    }
}

@Composable
private fun ScheduleDetails(title: String, content: String) {
    Column(verticalArrangement = Arrangement.Center) {
        SectionTitle(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = if (isSystemInDarkTheme()) DDD_BLACK_TEXT_PRIMARY else DDD_WHITE_TEXT_PRIMARY,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(8.dp))
        SectionText(
            text = content,
            style = MaterialTheme.typography.bodySmall,
            color = if (isSystemInDarkTheme()) DDD_BLACK_TEXT_SECONDARY else DDD_WHITE_TEXT_SECONDARY
        )
    }
}

private fun getSampleSchedules(): List<Schedule> {
    return listOf(
        Schedule(month = "6월", day = "11", title = "오리엔테이션", content = "커리큘럼에 대한 설명 문구 작성"),
        Schedule(month = "6월", day = "22", title = "부스팅 데이 1", content = "커리큘럼에 대한 설명 문구 작성"),
        Schedule(month = "7월", day = "06", title = "St. Patrick's Day", content = "Irish cultural celebration"),
        Schedule(month = "6월", day = "25", title = "April Fools' Day", content = "Day for jokes and pranks")
    )
}

@Preview(name = "Content", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun P1() {
    Content(
        onPressMyPage = {},
        onPressQrcode = {},
        onClickBackButton = {}
    )
}