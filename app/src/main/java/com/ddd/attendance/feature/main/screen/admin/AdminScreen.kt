package com.ddd.attendance.feature.main.screen.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ddd.attendance.feature.main.MainViewModel
import com.ddd.attendance.core.ui.theme.DDD_BLACK

@Composable
fun AdminScreen(
    navController: NavController,
    viewModel: MainViewModel
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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DDD_BLACK)
    ) {

    }
}