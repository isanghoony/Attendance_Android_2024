package com.ddd.attendance.ui.screen.member

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ddd.attendance.R
import com.ddd.attendance.ui.MainViewModel
import com.ddd.attendance.ui.component.DDDText
import com.ddd.attendance.ui.component.main.DDDMemberSituation
import com.ddd.attendance.ui.theme.DDD_BLACK
import com.ddd.attendance.ui.theme.DDD_NEUTRAL_GRAY_50
import com.ddd.attendance.ui.theme.DDD_WHITE

@Composable
fun MemberScreen(
    navController: NavController,
    viewModel: MainViewModel,
) {
    var showQr by remember { mutableStateOf(false) }

    Content(
        onClickBackButton = {
            navController.popBackStack()
        },
        onClickMyPage = {
            //MyPage 화면 전환 예정
        },
        showQr = showQr,
        onClickQrScan = { isClicked ->
            showQr = isClicked
            //QR 화면 전환 예정
        }
    )
}

@Composable
private fun Content(
    onClickBackButton: () -> Unit,
    onClickMyPage:() -> Unit,
    showQr: Boolean,
    onClickQrScan:(isClicked: Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = DDD_BLACK)
    ) {
        Column (
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .height(52.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.padding(start = 16.dp),
                    painter = painterResource(R.drawable.ic_main_logo),
                    contentDescription = "DDD logo"
                )

                Spacer(modifier = Modifier.weight(1F))

                Image(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .clickable { onClickMyPage() },
                    painter = painterResource(R.drawable.ic_40_profile_white),
                    contentDescription = "my page"
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(Modifier.height(12.dp))

                DDDText(
                    text = stringResource(R.string.member_attendance_status,"김디디"),
                    textStyle = MaterialTheme.typography.titleLarge,
                    color = DDD_WHITE,
                    fontWeight = FontWeight.W700
                )

                Spacer(Modifier.height(16.dp))

                DDDText(
                    text = stringResource(R.string.member_activity_period, "2025.03.12 - 2025.08.12"),
                    textStyle = MaterialTheme.typography.bodySmall,
                    color = DDD_NEUTRAL_GRAY_50,
                    fontWeight = FontWeight.W400,
                )

                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

@Preview
@Composable
private fun MemberScreenPreview() {
    Content(
        onClickBackButton = {},
        onClickMyPage = {},
        onClickQrScan = {},
        showQr = false
    )
}