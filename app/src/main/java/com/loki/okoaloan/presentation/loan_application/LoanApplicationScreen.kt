package com.loki.okoaloan.presentation.loan_application

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.loki.okoaloan.presentation.common.TopBar

@Composable
fun LoanApplicationScreen(
    navController: NavController
) {


    Scaffold( topBar = { TopBar(title = "Personal Information") }) {

        Box(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colors.surface)
        ) {

        }
    }
}

