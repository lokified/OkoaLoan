package com.loki.okoaloan.presentation.auth_screen

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dsc.form_builder.TextFieldState
import com.loki.okoaloan.presentation.common.ButtonSection
import com.loki.okoaloan.presentation.common.Input
import com.loki.okoaloan.presentation.common.TopBar
import com.loki.okoaloan.presentation.navigation.Screens
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {

        viewModel.authEvent.collectLatest { event ->

            when(event) {
                is AuthViewModel.AuthEvent.Loading -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Loading",
                        duration = SnackbarDuration.Long
                    )
                }

                is AuthViewModel.AuthEvent.LoginSuccess -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        duration = SnackbarDuration.Short
                    )
                    navController.navigate(Screens.HomeScreen.route)
                }

                is AuthViewModel.AuthEvent.RegisterSuccess -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        duration = SnackbarDuration.Long
                    )
                }

                is AuthViewModel.AuthEvent.Error -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.error
                    )
                }
            }
        }
    }

    Scaffold(
        topBar = { TopBar(title = "Welcome") },
        scaffoldState = scaffoldState
    ) {

        Box(modifier = Modifier.background(MaterialTheme.colors.surface)) {
            SignUpSection()
        }
    }
}


@Composable
fun SignUpSection() {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
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
                        modifier = Modifier.padding(16.dp)
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
    viewModel: AuthViewModel = hiltViewModel()
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val formState = remember { viewModel.loginFormState }

    val phone = formState.getState<TextFieldState>("Phone")
    val password = formState.getState<TextFieldState>("Password")

    Box(modifier = modifier) {

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
                    viewModel.loginUser(
                        phoneNumber = phone.value,
                        password = password.value
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpFormSection(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val formState = remember { viewModel.registerFormState }

    val phone = formState.getState<TextFieldState>("Phone")
    val password = formState.getState<TextFieldState>("Password")
    val conPassword = formState.getState<TextFieldState>("Confirm Password")

    Box(modifier = modifier) {

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
                    viewModel.registerUser(
                        phoneNumber = phone.value,
                        password = password.value
                    )
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