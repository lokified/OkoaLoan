package com.loki.okoaloan.presentation.loan_application

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dsc.form_builder.ChoiceState
import com.dsc.form_builder.SelectState
import com.dsc.form_builder.TextFieldState
import com.loki.okoaloan.presentation.common.*

@Composable
fun LoanApplicationScreen(
    navController: NavController
) {


    Scaffold( topBar = { TopBar(title = "Basic Information") }) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
        ) {

            BasicInfoForm(modifier = Modifier.padding(16.dp))
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BasicInfoForm(
    modifier: Modifier = Modifier
) {

    val scrollState = rememberScrollState()
    val viewModel = LoanApplicationViewModel()
    val formState = remember{ viewModel.basicInfoState }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = modifier
        .fillMaxWidth()
        .verticalScroll(scrollState)) {

        val firstName = formState.getState<TextFieldState>("firstName")
        val lastName = formState.getState<TextFieldState>("lastName")
        val idNumber = formState.getState<TextFieldState>("id")
        val email = formState.getState<TextFieldState>("email")
        val gender = formState.getState<ChoiceState>("gender")

        InputLabel(labelNumber = "1", label = "What is your full name?")
        AltInput(
            placeholder = "Input first name",
            value = firstName.value,
            onValueChange = { firstName.change(it) },
            errorMessage = firstName.errorMessage,
            isError = firstName.hasError
        )

        AltInput(
            placeholder = "Input last name",
            value = lastName.value,
            onValueChange = { lastName.change(it) },
            errorMessage = lastName.errorMessage,
            isError = lastName.hasError
        )

        InputLabel(labelNumber = "2", label = "What is your gender")
        val options = listOf("Male", "Female")

        Column {
            Row(
                verticalAlignment = CenterVertically,
                horizontalArrangement = Center
            ) {
                options.forEach {

                    Row(
                        verticalAlignment = CenterVertically,
                        horizontalArrangement = Center
                    ) {

                        RadioButton(
                            selected = gender.value == it,
                            onClick = { gender.change(it) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = MaterialTheme.colors.primary
                            )
                        )
                        Text(text = it, fontSize = 16.sp)

                    }
                }
                Spacer(modifier = Modifier.width(24.dp))
            }

            if (gender.hasError) {
                Text(
                    text = gender.errorMessage,
                    fontSize = 12.sp,
                    color = Color.Red
                )
            }
        }


        InputLabel(labelNumber = "3", label = "What is your national ID?")
        AltInput(
            placeholder = "Input your National ID",
            value = idNumber.value,
            onValueChange = { idNumber.change(it) },
            keyboardType = KeyboardType.Number,
            errorMessage = idNumber.errorMessage,
            isError = idNumber.hasError
        )

        InputLabel(labelNumber = "4", label = "What is your Email?")
        AltInput(
            placeholder = "Input your Email",
            value = email.value,
            onValueChange = { email.change(it) },
            keyboardType = KeyboardType.Email,
            errorMessage = email.errorMessage,
            isError = email.hasError
        )

        ButtonSection(label = "Continue") {

            keyboardController?.hide()
            if (formState.validate()) {

            }
        }
    }
}