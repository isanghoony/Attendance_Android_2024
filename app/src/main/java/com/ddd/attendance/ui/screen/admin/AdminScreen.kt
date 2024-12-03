package com.ddd.attendance.ui.screen.admin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.ddd.attendance.ui.component.main.DDDAdminSituationItem
import com.ddd.attendance.ui.theme.DDD_BLACK
import com.ddd.attendance.ui.theme.DDD_BORDER_INACTIVE
import com.ddd.attendance.ui.theme.DDD_WHITE

@Composable
fun AdminScreen(
    navController: NavController,
    viewModel: MainViewModel,
) {
    Content(
        onClickBackButton = {
            navController.popBackStack()
        },
        onClickMyPage = {

        }
    )
}

@Composable
private fun Content(
    onClickBackButton:() -> Unit,
    onClickMyPage:() -> Unit
) {
    Column(
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
        ) {

        }
    }
}

@Preview
@Composable
private fun AdminScreenPreview() {
    Content(
        onClickBackButton = {},
        onClickMyPage = {}
    )
}