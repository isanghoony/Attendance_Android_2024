package com.ddd.attendance.ui.screen.member

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ddd.attendance.R
import com.ddd.attendance.ui.MainViewModel
import com.ddd.attendance.ui.component.DDDText
import com.ddd.attendance.ui.component.main.DDDSituation
import com.ddd.attendance.ui.component.main.DDDToolTip
import com.ddd.attendance.ui.theme.DDD_BLACK
import com.ddd.attendance.ui.theme.DDD_BORDER_INACTIVE
import com.ddd.attendance.ui.theme.DDD_WHITE

@Composable
fun MemberScreen(
    navController: NavController,
    viewModel: MainViewModel,
) {
    Content(
        onClickBackButton = {
            navController.popBackStack()
        },
        onClickMyPage = {
            //MyPage 화면 전환 예정
        },
        onClickQrScan = {
            //QR 화면 전환 예정
        }
    )
}

@Composable
private fun Content(
    onClickBackButton: () -> Unit,
    onClickMyPage:() -> Unit,
    onClickQrScan:() -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = DDD_BLACK)
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
                .background(color = DDD_BLACK)
        ) {
            Spacer(Modifier.height(12.dp))

            DDDText(
                text = stringResource(R.string.member_welcome),
                color = DDD_WHITE,
                fontWeight = FontWeight.W700,
                fontSize = 24.sp
            )
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "(11th)",
                    color = DDD_BORDER_INACTIVE,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W700,
                    fontStyle = FontStyle.Italic
                )

                DDDText(
                    text = stringResource(R.string.name, "김디디"),
                    color = DDD_WHITE,
                    fontWeight = FontWeight.W700,
                    fontSize = 24.sp
                )
            }

            Spacer(Modifier.height(40.dp))

            DDDSituation(
                attendanceCount = 4,
                tardyCount = 2,
                absentCount = 9
            )

            Spacer(Modifier.weight(1F))

            DDDToolTip(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.member_qr_scan_request)
            ) { }

            Spacer(Modifier.height(12.dp))

            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { onClickQrScan() },
                painter = painterResource(R.drawable.ic_qr_code),
                contentDescription = "qr scan"
            )

            Spacer(Modifier.height(20.dp))
        }
    }
}

@Preview
@Composable
private fun MemberScreenPreview() {
    Content(
        onClickBackButton = {},
        onClickMyPage = {},
        onClickQrScan = {}
    )
}