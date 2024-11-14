package com.ddd.attendance.ui.screen.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ddd.attendance.R
import com.ddd.attendance.ui.MainViewModel
import com.ddd.attendance.ui.theme.DDD_BLACK

@Composable
fun AdminScreen(
    navController: NavController,
    viewModel: MainViewModel,
) {
    Content(
        onClickBackButton = {
            navController.popBackStack()
        }
    )
}

@Composable
private fun Content(
    onClickBackButton: () -> Unit,
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
            Icon(
                modifier = Modifier.padding(start = 16.dp),
                painter = painterResource(R.drawable.ic_main_logo),
                contentDescription = "Text with Icon Button",
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.weight(1F))

            Icon(
                modifier = Modifier.padding(end = 16.dp),
                painter = painterResource(R.drawable.ic_40_profile_white),
                contentDescription = "Text with Icon Button",
                tint = Color.Unspecified
            )
        }
    }
}

@Preview
@Composable
private fun AdminScreenPreview() {
    Content {

    }
}