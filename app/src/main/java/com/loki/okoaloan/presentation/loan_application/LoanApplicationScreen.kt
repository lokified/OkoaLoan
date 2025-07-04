package com.loki.okoaloan.presentation.loan_application

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dsc.form_builder.ChoiceState
import com.dsc.form_builder.TextFieldState
import com.loki.okoaloan.presentation.navigation.Screens
import com.loki.okoaloan.presentation.common.*

@Composable
fun LoanApplicationScreen(
    openScreen: (String) -> Unit
) {


    Scaffold( topBar = { TopBar(title = "Basic Information") }) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
        ) {

            BasicInfoForm(
                modifier = Modifier.padding(16.dp),
                openScreen = openScreen
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BasicInfoForm(
    modifier: Modifier = Modifier,
    openScreen: (String) -> Unit
) {

    val viewModel = hiltViewModel<LoanApplicationViewModel>()
    val formState = remember{ viewModel.basicInfoState }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = modifier
        .fillMaxWidth()) {

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
                                selectedColor = MaterialTheme.colorScheme.primary
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
                    color = MaterialTheme.colorScheme.error
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
                openScreen(Screens.PersonalInfoScreen.route)
            }
        }
    }
}
