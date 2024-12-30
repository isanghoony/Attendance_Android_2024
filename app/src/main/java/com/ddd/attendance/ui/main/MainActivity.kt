package com.ddd.attendance.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import com.ddd.attendance.ui.theme.AttendanceTheme
import com.ddd.attendance.ui.theme.DDD_BLACK
import com.ddd.attendance.ui.theme.DDD_WHITE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val systemDarkTheme = isSystemInDarkTheme()

            val statusBarColor = if (systemDarkTheme) DDD_BLACK.toArgb() else DDD_WHITE.toArgb()
            val isLightStatusBars = !systemDarkTheme

            window.statusBarColor = statusBarColor
            WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = isLightStatusBars

            AttendanceTheme(darkTheme = systemDarkTheme) {
                MainScreen()
            }
        }
    }
}