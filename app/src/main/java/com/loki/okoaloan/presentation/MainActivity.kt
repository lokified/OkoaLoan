package com.loki.okoaloan.presentation

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.loki.okoaloan.presentation.ui.theme.OkoaLoanTheme
import com.loki.okoaloan.presentation.navigation.Navigation
import com.loki.okoaloan.util.SnackbarManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OkoaLoanTheme {
                Box(modifier = Modifier
                    .background(color = MaterialTheme.colors.surface)
                    .fillMaxSize()) {

                    val appState = rememberAppState()
                    
                    Scaffold(
                        snackbarHost = {
                            SnackbarHost(
                                hostState = it,
                                modifier = Modifier.padding(8.dp),
                                snackbar = { snackbarData -> 
                                    Snackbar(
                                        snackbarData = snackbarData,
                                        contentColor = Color.Black
                                    )
                                }
                            )
                        },
                        scaffoldState = appState.scaffoldState
                    ) {
                        Navigation(
                            appState = appState
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember(scaffoldState,navController, snackbarManager, resources, coroutineScope) {
        OkoaLoanAppState(
            scaffoldState = scaffoldState,
            navController = navController,
            snackbarManager = snackbarManager,
            resources = resources,
            coroutineScope = coroutineScope
        )
    }

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}