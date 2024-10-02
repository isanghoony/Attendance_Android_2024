package com.ddd.attendance.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ddd.attendance.ui.theme.AttendanceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginProcessActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AttendanceTheme {
                LoginProcessScreen()
            }
        }
    }
}