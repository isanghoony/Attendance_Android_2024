package com.ddd.attendance.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ddd.attendance.ui.screen.admin.AdminScreen
import com.ddd.attendance.ui.screen.invitationcode.InvitationCodeScreen
import com.ddd.attendance.ui.screen.login.LoginScreen
import com.ddd.attendance.ui.screen.member.MemberScreen
import com.ddd.attendance.ui.theme.DDD_BLACK

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val viewModel: MainViewModel = hiltViewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = DDD_BLACK)
    ) {
        NavHost(
            navController = navController,
            startDestination = MainScreenName.MAIN_MEMBER.name
        ) {
            //회원
            composable(route = MainScreenName.MAIN_MEMBER.name) {
                MemberScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }
            //운영진
            composable(route = MainScreenName.MAIN_ADMIN.name) {
                AdminScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}

enum class MainScreenName {
    MAIN,
    MAIN_MEMBER,
    MAIN_ADMIN,

}