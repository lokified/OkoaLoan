package com.loki.okoaloan.presentation.change_password

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.dsc.form_builder.TextFieldState
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
    val viewModel = ChangePasswordViewModel()
    val keyboardController = LocalSoftwareKeyboardController.current
    val formState = remember { viewModel.formState }

    val oldPassword = formState.getState<TextFieldState>("Old Password")
    val newPassword = formState.getState<TextFieldState>("New Password")
    val conPassword = formState.getState<TextFieldState>("Confirm Password")

    Box(modifier = modifier) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Input(
                placeholder = "Enter old Password",
                label = "Old Password",
                value = oldPassword.value,
                onValueChange = { oldPassword.change(it) },
                keyboardType = KeyboardType.Password,
                errorMessage = oldPassword.errorMessage,
                isError = oldPassword.hasError
            )

            Input(
                placeholder = "Enter new Password",
                label = "New Password",
                value = newPassword.value,
                onValueChange = { newPassword.change(it) },
                keyboardType = KeyboardType.Password,
                errorMessage = newPassword.errorMessage,
                isError = newPassword.hasError
            )

            Input(
                placeholder = "Confirm new Password",
                label = "Confirm Password",
                value = conPassword.value,
                onValueChange = { conPassword.change(it) },
                keyboardType = KeyboardType.Password,
                errorMessage = conPassword.errorMessage,
                isError = conPassword.hasError
            )

            ButtonSection(label = "Update") {
                keyboardController?.hide()

                if(formState.validate()) {

                }
            }
        }

    }
}