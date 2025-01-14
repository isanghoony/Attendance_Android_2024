package com.ddd.attendance.ui.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import com.ddd.attendance.ui.main.MainScreen
import com.ddd.attendance.ui.theme.AttendanceTheme
import com.ddd.attendance.ui.theme.DDD_BLACK
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginProcessActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = DDD_BLACK.toArgb()
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = false
        setContent {
            AttendanceTheme {
                LoginProcessScreen()
            }
        }
    }
}