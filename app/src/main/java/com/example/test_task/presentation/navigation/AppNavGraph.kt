package com.example.test_task.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.test_task.presentation.authorization.AuthorizationScreen
import com.example.test_task.presentation.main.MainScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(21, 21, 21)
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppRoutes.AUTHORIZATION,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppRoutes.AUTHORIZATION) {
                AuthorizationScreen(
                    onMainScreen = {
                        navController.navigate(AppRoutes.MAIN)
                    },
                )
            }

            composable(AppRoutes.MAIN) {
                MainScreen()
            }
        }
    }
}
