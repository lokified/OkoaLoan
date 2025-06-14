package com.loki.okoaloan.presentation.limit_confirmation_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.loki.okoaloan.presentation.common.ButtonSection
import com.loki.okoaloan.presentation.common.TopBar
import com.loki.okoaloan.presentation.navigation.Screens

@Composable
fun LimitProcessingScreen(
    clearAndNavigate: (String) -> Unit
) {

    Scaffold( topBar = { TopBar(title = "Limit Confirmation") } ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            LimitProcessingSection(
                clearAndNavigate = clearAndNavigate,
                modifier = Modifier.padding(24.dp)
            )
        }
    }
}

@Composable
fun LimitProcessingSection(
    modifier: Modifier = Modifier,
    clearAndNavigate: (String) -> Unit
) {

    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(imageVector = Icons.Default.History, contentDescription = null)
            Text(text = "Limit Processing")
            Text(
                text = "Your loan limit will be updated and you can process with your first loan",
                fontSize = 12.sp
            )
            ButtonSection(label = "Back To Home") {
                clearAndNavigate(Screens.HomeScreen.route)
            }
        }
    }
}
