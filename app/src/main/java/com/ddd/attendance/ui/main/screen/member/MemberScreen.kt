package com.ddd.attendance.ui.main.screen.member

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.ddd.attendance.ui.scanner.QrScanBottomSheet
import com.ddd.attendance.ui.theme.DDD_BLACK
import com.ddd.attendance.ui.theme.DDD_NEUTRAL_BLUE_20
import com.ddd.attendance.ui.theme.DDD_NEUTRAL_GRAY_20
import com.ddd.attendance.ui.theme.DDD_NEUTRAL_GRAY_90
import com.ddd.attendance.ui.theme.DDD_WHITE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val showBottomSheet = remember { mutableStateOf(false) }
    val toggleBottomSheet = { showBottomSheet.value = !showBottomSheet.value }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Content(
            onPressMyPage = {},
            onPressQrcode = {
                toggleBottomSheet()
            },
            onClickBackButton = {
                navController.popBackStack()
            }
        )

        if (showBottomSheet.value) {
            QrScanBottomSheet()
        }
    }
}

@Composable
private fun Content(
    onPressMyPage: () -> Unit,
    onPressQrcode: () -> Unit,
    onClickBackButton: () -> Unit,
) {
    val schedules = getSchedules()

    BackHandler { onClickBackButton() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DDD_BLACK)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                Spacer(Modifier.height(36.dp))
                HeaderSection(onPressMyPage, onPressQrcode)
            }

            item {
                Spacer(Modifier.height(20.dp))
                BodySection()
            }

            items(schedules) { schedule ->
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
        onPressQrcode = onPressQrcode,
        onPressMyPage = onPressMyPage
    )
}

@Composable
private fun BodySection() {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp)
    ) {
        DDDText(
            text = stringResource(R.string.member_attendance_status, "김디디"),
            color = DDD_WHITE,
            textStyle = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(16.dp))

        DDDText(
            text = stringResource(R.string.member_activity_period, "2025.03.12 ~ 2025.08.12"),
            color = DDD_NEUTRAL_GRAY_20,
            textStyle = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Normal
        )

        Spacer(Modifier.height(8.dp))

        DDDMemberSituation(
            attendanceCount = 8,
            tardyCount = 2,
            absentCount = 1
        )

        Spacer(Modifier.height(56.dp))

        DDDText(
            text = stringResource(R.string.member_th_schedule, "12"),
            color = DDD_WHITE,
            textStyle = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium
        )

        Spacer(Modifier.height(16.dp))
    }
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
            .background(DDD_NEUTRAL_GRAY_90)
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ScheduleDateBox(schedule)
            Spacer(Modifier.width(12.dp))
            ScheduleDetails(schedule)
        }
    }
}

@Composable
private fun ScheduleDateBox(schedule: Schedule) {
    Box(
        modifier = Modifier
            .size(54.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(DDD_NEUTRAL_BLUE_20),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            DDDText(
                text = schedule.month,
                color = DDD_BLACK,
                textStyle = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium
            )
            Spacer(Modifier.height(4.dp))
            DDDText(
                text = schedule.day,
                color = DDD_BLACK,
                textStyle = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun ScheduleDetails(schedule: Schedule) {
    Column(verticalArrangement = Arrangement.Center) {
        DDDText(
            text = schedule.title,
            color = DDD_WHITE,
            textStyle = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(8.dp))
        DDDText(
            text = schedule.content,
            color = DDD_NEUTRAL_GRAY_20,
            textStyle = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
@Preview(name = "멤버")
private fun P1() {
    Content(
        onPressMyPage = {},
        onPressQrcode = {},
        onClickBackButton = {}
    )

}

private fun getSchedules(): List<Schedule> = listOf(
    Schedule(month = "6월", day = "11", title = "오리엔테이션", content = "커리큘럼에 대한 설명 문구 작성"),
    Schedule(month = "6월", day = "22", title = "부스팅 데이 1", content = "커리큘럼에 대한 설명 문구 작성"),
    Schedule(month = "7월", day = "06", title = "St. Patrick's Day", content = "Irish cultural celebration"),
    Schedule(month = "6월", day = "25", title = "April Fools' Day", content = "Day for jokes and pranks"),
    Schedule(month = "9월", day = "21", title = "부스팅 데이 2", content = "설명 문구 작성"),
    Schedule(month = "10월", day = "11", title = "직군 세션", content = "놀자 놀자")
)