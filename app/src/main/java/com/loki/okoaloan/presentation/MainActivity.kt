package com.loki.okoaloan.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.loki.okoaloan.presentation.ui.theme.OkoaLoanTheme
import com.loki.okoaloan.presentation.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OkoaLoanTheme {
                Box(modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surface)
                    .fillMaxSize()) {

                    val appState = rememberAppState()

                    Navigation(
                        appState = appState
                    )
                }
            }
        }
    }
}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
) = remember(navController) {
        OkoaLoanAppState(
            navController = navController,
        )
    }

