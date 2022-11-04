package com.loki.okoaloan.presentation.welcome_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dsc.form_builder.TextFieldState
import com.loki.okoaloan.presentation.Screens
import com.loki.okoaloan.presentation.common.ButtonSection
import com.loki.okoaloan.presentation.common.Input
import com.loki.okoaloan.presentation.common.TopBar

@Composable
fun WelcomeScreen(
    navController: NavController
) {

    Scaffold(topBar = { TopBar(title = "Welcome") }) {

        Box(modifier = Modifier.background(MaterialTheme.colors.surface)) {
            SignUpSection(
                navController = navController
            )
        }

    }
}


@Composable
fun SignUpSection(
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var selected by remember {
            mutableStateOf("Register")
        }

        ToggleButton(
            onSelected = {
                selected = it
            },
            content = {

                if(selected == "Register") {
                    SignUpFormSection(
                        modifier = Modifier.padding(16.dp)
                    )

                } else {
                    LoginFormSection(
                        modifier = Modifier.padding(16.dp),
                        navController = navController
                    )

                }
            }
        )
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginFormSection(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val viewModel = AuthViewModel()

    val keyboardController = LocalSoftwareKeyboardController.current
    val formState = remember { viewModel.loginFormState }

    val phone = formState.getState<TextFieldState>("Phone")
    val password = formState.getState<TextFieldState>("Password")

    Box(modifier = modifier.fillMaxSize()) {

        Column {

            Input(
                placeholder = "Enter your phone number",
                label = "Phone",
                value = phone.value,
                onValueChange = { phone.change(it) },
                errorMessage = phone.errorMessage,
                isError = phone.hasError
            )

            Input(
                placeholder = "Enter your password",
                label = "Password",
                value = password.value,
                onValueChange = { password.change(it) },
                keyboardType = KeyboardType.Password,
                errorMessage = password.errorMessage,
                isError = password.hasError
            )

            ButtonSection(label = "Login") {
                keyboardController?.hide()

                if (formState.validate()) {
                    navController.navigate(Screens.HomeScreen.route)
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpFormSection(
    modifier: Modifier = Modifier
) {
    val viewModel = AuthViewModel()

    val keyboardController = LocalSoftwareKeyboardController.current
    val formState = remember { viewModel.registerFormState }

    val phone = formState.getState<TextFieldState>("Phone")
    val password = formState.getState<TextFieldState>("Password")
    val conPassword = formState.getState<TextFieldState>("Confirm Password")

    Box(modifier = modifier.fillMaxSize()) {

        Column {

            Input(
                placeholder = "Enter your phone number",
                label = "Phone",
                value = phone.value,
                onValueChange = { phone.change(it) },
                errorMessage = phone.errorMessage,
                isError = phone.hasError
            )

            Input(
                placeholder = "Enter your password",
                label = "Password",
                value = password.value,
                onValueChange = { password.change(it) },
                keyboardType = KeyboardType.Password,
                errorMessage = password.errorMessage,
                isError = password.hasError
            )

            Input(
                placeholder = "Confirm Password",
                label = "Confirm Password",
                value = conPassword.value,
                onValueChange = { conPassword.change(it) },
                keyboardType = KeyboardType.Password,
                errorMessage = conPassword.errorMessage,
                isError = conPassword.hasError
            )

            ButtonSection(label = "Register") {
                keyboardController?.hide()

                if (formState.validate()) {

                }
            }


        }
    }
}



@Composable
fun ToggleButton(
    options: List<String> = listOf(
        "Register",
        "Login",
    ),
    onSelected: (String) -> Unit,
    content: @Composable() () -> Unit
) {

    var selectedOption by remember {
        mutableStateOf("Register")
    }

    val onSelectionChange = { text: String ->
        selectedOption = text
    }


    Column {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.align(CenterHorizontally)
        ) {

            options.forEach { text ->

                Text(
                    text = text,
                    style = MaterialTheme.typography.body1,
                    color = Color.Black,
                    modifier = Modifier
                        .clip(RoundedCornerShape(100.dp))
                        .clickable {
                            onSelectionChange(text)
                            onSelected(text)
                        }
                        .background(
                            if (text == selectedOption) {
                                Color(0xFFE9BB16)
                            } else {
                                Color.Transparent
                            }
                        )
                        .padding(vertical = 8.dp, horizontal = 12.dp)
                )
            }
        }

        content()
    }
}