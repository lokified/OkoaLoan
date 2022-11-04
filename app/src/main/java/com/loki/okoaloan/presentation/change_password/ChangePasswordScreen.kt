package com.loki.okoaloan.presentation.change_password

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.loki.okoaloan.presentation.common.ButtonSection
import com.loki.okoaloan.presentation.common.Input
import com.loki.okoaloan.presentation.common.TopBar

@Composable
fun ChangePasswordScreen() {
    
    Scaffold(topBar = { TopBar(title = "Change Password") }) {
        
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
        ) {
            
            FormSection(
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FormSection(
    modifier: Modifier = Modifier
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Box(modifier = modifier) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Input(
                placeholder = "Enter old Password",
                label = "Old Password",
                value = {

                },
                keyboardType = KeyboardType.Password
            )

            Input(
                placeholder = "Enter new Password",
                label = "New Password",
                value = {

                },
                keyboardType = KeyboardType.Password
            )

            ButtonSection(label = "Update") {
                keyboardController?.hide()
            }
        }

    }
}