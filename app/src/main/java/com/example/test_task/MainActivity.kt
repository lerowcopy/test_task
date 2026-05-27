package com.example.test_task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.test_task.presentation.navigation.AppNavGraph
import com.example.test_task.ui.theme.Test_taskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                scrim = android.graphics.Color.rgb(21, 21, 21)
            ),
            navigationBarStyle = SystemBarStyle.dark(
                scrim = android.graphics.Color.rgb(36, 37, 42)
            )
        )
        setContent {
            Test_taskTheme {
                AppNavGraph()
            }
        }
    }
}
